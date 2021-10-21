package com.danielomeara.contactsapplication.featurecontacts.presentation.addeditcontact

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.danielomeara.contactsapplication.featurecontacts.presentation.addeditcontact.components.TransparentHintTextField
import com.danielomeara.contactsapplication.ui.theme.phoneIcon
import kotlinx.coroutines.flow.collectLatest


@Composable
fun AddEditContactScreen(
    navController: NavController,
    viewModel: AddEditContactViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val nameState = viewModel.contactName.value
    val phoneNumberState = viewModel.contactPhoneNumber.value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditContactViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditContactViewModel.UiEvent.SaveContact -> {
                    navController.navigateUp()
                }
                is AddEditContactViewModel.UiEvent.MakePhoneCall -> {
                    context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${event.phoneNumber}")))
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditContactEvent.SaveContact)
                },
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save contact")
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Add new contact") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TransparentHintTextField(
                text = nameState.text,
                hint = nameState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditContactEvent.EnteredName(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditContactEvent.ChangeNameFocus(it))
                },
                isHintVisible = nameState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TransparentHintTextField(
                text = phoneNumberState.text,
                hint = phoneNumberState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditContactEvent.EnteredPhoneNumber(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditContactEvent.ChangePhoneNumberFocus(it))
                },
                isHintVisible = phoneNumberState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
            )

            IconButton(
                onClick = {
                    viewModel.onEvent(AddEditContactEvent.MakePhoneCall(phoneNumberState.text))
                },
                modifier = Modifier.padding(top = 32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Call",
                    modifier = Modifier.size(100.dp),
                    tint = MaterialTheme.colors.phoneIcon
                )
            }
        }
    }

}