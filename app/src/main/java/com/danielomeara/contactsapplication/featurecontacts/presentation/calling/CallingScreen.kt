package com.danielomeara.contactsapplication.featurecontacts.presentation.calling

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danielomeara.contactsapplication.featurecontacts.presentation.calling.components.NumberButton
import com.danielomeara.contactsapplication.featurecontacts.presentation.util.CustomBottomNavigation
import com.danielomeara.contactsapplication.featurecontacts.presentation.util.Screen
import com.danielomeara.contactsapplication.ui.theme.phoneIcon
import kotlinx.coroutines.flow.collectLatest

@ExperimentalFoundationApi
@Composable
fun CallingScreen(
    navController: NavController,
    viewModel: CallingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val configuration = LocalConfiguration.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is CallingViewModel.UiEvent.MakePhoneCall -> {
                    context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${event.phoneNumber}")))
                }
                is CallingViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            CustomBottomNavigation(
                selectedRoute = Screen.CallingScreen.route,
                onItemSelected = {
                    navController.navigate(it.route)
                })
        }
    ) { innerPadding ->
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    Text(
                        text = state.phoneNumber,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(0.5f),
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center
                    )
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row{
                            NumberButton(
                                onNumberClick = {
                                    viewModel.onEvent(CallingEvent.ButtonPressed("1"))
                                },
                                onNumberLongClick = {
                                    viewModel.onEvent(CallingEvent.ButtonLongPressed("911"))
                                },
                                headText = "1",
                                subText = "911",
                                modifier = Modifier.weight(0.33f)
                            )
                            NumberButton(
                                onNumberClick = {
                                    viewModel.onEvent(CallingEvent.ButtonPressed("2"))
                                },
                                headText = "2",
                                subText = "ABC",
                                modifier = Modifier.weight(0.33f)
                            )
                            NumberButton(
                                onNumberClick = {
                                    viewModel.onEvent(CallingEvent.ButtonPressed("3"))
                                },
                                headText = "3",
                                subText = "DEF",
                                modifier = Modifier.weight(0.33f)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            NumberButton(
                                onNumberClick = {
                                    viewModel.onEvent(CallingEvent.ButtonPressed("4"))
                                },
                                headText = "4",
                                subText = "GHI",
                                modifier = Modifier.weight(0.33f)
                            )
                            NumberButton(
                                onNumberClick = {
                                    viewModel.onEvent(CallingEvent.ButtonPressed("5"))
                                },
                                headText = "5",
                                subText = "JKL",
                                modifier = Modifier.weight(0.33f)
                            )
                            NumberButton(
                                onNumberClick = {
                                    viewModel.onEvent(CallingEvent.ButtonPressed("6"))
                                },
                                headText = "6",
                                subText = "MNO",
                                modifier = Modifier.weight(0.33f)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            NumberButton(
                                onNumberClick = {
                                    viewModel.onEvent(CallingEvent.ButtonPressed("7"))
                                },
                                headText = "7",
                                subText = "PQRS",
                                modifier = Modifier.weight(0.33f)
                            )
                            NumberButton(
                                onNumberClick = {
                                    viewModel.onEvent(CallingEvent.ButtonPressed("8"))
                                },
                                headText = "8",
                                subText = "TUV",
                                modifier = Modifier.weight(0.33f)
                            )
                            NumberButton(
                                onNumberClick = {
                                    viewModel.onEvent(CallingEvent.ButtonPressed("9"))
                                },
                                headText = "9",
                                subText = "WXYZ",
                                modifier = Modifier.weight(0.33f)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            NumberButton(
                                onNumberClick = {
                                },
                                headText = "*",
                                modifier = Modifier.weight(0.33f)
                            )
                            NumberButton(
                                onNumberClick = {
                                    viewModel.onEvent(CallingEvent.ButtonPressed("0"))
                                },
                                headText = "0",
                                subText = "+",
                                modifier = Modifier.weight(0.33f)
                            )
                            NumberButton(
                                onNumberClick = {
                                },
                                headText = "#",
                                modifier = Modifier.weight(0.33f)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            IconButton(
                                onClick = {
                                    navController.navigate(
                                        Screen.AddEditContactScreen.route +
                                                "?contactNumber=${state.phoneNumber}"
                                    )
                                },
                                modifier = Modifier.weight(0.33f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PersonAdd,
                                    contentDescription = "Add contact"
                                )
                            }
                            IconButton(
                                onClick = {
                                    viewModel.onEvent(CallingEvent.MakePhoneCall(state.phoneNumber))
                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colors.phoneIcon)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Phone,
                                    contentDescription = "Make call",
                                    tint = Color.Black
                                )
                            }
                            IconButton(
                                onClick = {
                                    viewModel.onEvent(CallingEvent.BackspacePressed)
                                },
                                modifier = Modifier.weight(0.33f)
                            ) {
                                Icon(imageVector = Icons.Default.Backspace, contentDescription = "Backspace")
                            }
                        }
                    }
                }
            }
            else -> {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(top = 64.dp)
                ) {
                    Text(
                        text = state.phoneNumber,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.h4,
                        textAlign = TextAlign.Center
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        NumberButton(
                            onNumberClick = {
                                viewModel.onEvent(CallingEvent.ButtonPressed("1"))
                            },
                            onNumberLongClick = {
                                viewModel.onEvent(CallingEvent.ButtonLongPressed("911"))
                            },
                            headText = "1",
                            subText = "911",
                            modifier = Modifier.weight(0.33f)
                        )
                        NumberButton(
                            onNumberClick = {
                                viewModel.onEvent(CallingEvent.ButtonPressed("2"))
                            },
                            headText = "2",
                            subText = "ABC",
                            modifier = Modifier.weight(0.33f)
                        )
                        NumberButton(
                            onNumberClick = {
                                viewModel.onEvent(CallingEvent.ButtonPressed("3"))
                            },
                            headText = "3",
                            subText = "DEF",
                            modifier = Modifier.weight(0.33f)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        NumberButton(
                            onNumberClick = {
                                viewModel.onEvent(CallingEvent.ButtonPressed("4"))
                            },
                            headText = "4",
                            subText = "GHI",
                            modifier = Modifier.weight(0.33f)
                        )
                        NumberButton(
                            onNumberClick = {
                                viewModel.onEvent(CallingEvent.ButtonPressed("5"))
                            },
                            headText = "5",
                            subText = "JKL",
                            modifier = Modifier.weight(0.33f)
                        )
                        NumberButton(
                            onNumberClick = {
                                viewModel.onEvent(CallingEvent.ButtonPressed("6"))
                            },
                            headText = "6",
                            subText = "MNO",
                            modifier = Modifier.weight(0.33f)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        NumberButton(
                            onNumberClick = {
                                viewModel.onEvent(CallingEvent.ButtonPressed("7"))
                            },
                            headText = "7",
                            subText = "PQRS",
                            modifier = Modifier.weight(0.33f)
                        )
                        NumberButton(
                            onNumberClick = {
                                viewModel.onEvent(CallingEvent.ButtonPressed("8"))
                            },
                            headText = "8",
                            subText = "TUV",
                            modifier = Modifier.weight(0.33f)
                        )
                        NumberButton(
                            onNumberClick = {
                                viewModel.onEvent(CallingEvent.ButtonPressed("9"))
                            },
                            headText = "9",
                            subText = "WXYZ",
                            modifier = Modifier.weight(0.33f)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        NumberButton(
                            onNumberClick = {
                            },
                            headText = "*",
                            modifier = Modifier.weight(0.33f)
                        )
                        NumberButton(
                            onNumberClick = {
                                viewModel.onEvent(CallingEvent.ButtonPressed("0"))
                            },
                            headText = "0",
                            subText = "+",
                            modifier = Modifier.weight(0.33f)
                        )
                        NumberButton(
                            onNumberClick = {
                            },
                            headText = "#",
                            modifier = Modifier.weight(0.33f)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(
                                    Screen.AddEditContactScreen.route +
                                            "?contactNumber=${state.phoneNumber}"
                                )
                            },
                            modifier = Modifier.weight(0.33f)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PersonAdd,
                                contentDescription = "Add contact"
                            )
                        }
                        IconButton(
                            onClick = {
                                viewModel.onEvent(CallingEvent.MakePhoneCall(state.phoneNumber))
                            },
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colors.phoneIcon)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "Make call",
                                tint = Color.Black
                            )
                        }
                        IconButton(
                            onClick = {
                                viewModel.onEvent(CallingEvent.BackspacePressed)
                            },
                            modifier = Modifier.weight(0.33f)
                        ) {
                            Icon(imageVector = Icons.Default.Backspace, contentDescription = "Backspace")
                        }
                    }
                }
            }
        }
    }
}