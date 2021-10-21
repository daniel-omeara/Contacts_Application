package com.danielomeara.contactsapplication.featurecontacts.presentation.contacts

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.hilt.navigation.compose.hiltViewModel
import com.danielomeara.contactsapplication.featurecontacts.presentation.contacts.components.ContactItem
import com.danielomeara.contactsapplication.featurecontacts.presentation.contacts.components.OrderSection
import com.danielomeara.contactsapplication.featurecontacts.presentation.contacts.components.SearchView
import com.danielomeara.contactsapplication.featurecontacts.presentation.util.CustomBottomNavigation
import com.danielomeara.contactsapplication.featurecontacts.presentation.util.Screen

@ExperimentalAnimationApi
@Composable
fun ContactsScreen(
    navController: NavController,
    viewModel: ContactsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Contacts") },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.AddEditContactScreen.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.PersonAdd,
                            contentDescription = "Add contact"
                        )
                    }
                    IconButton(
                        onClick = {
                            viewModel.onEvent(ContactsEvent.ToggleOrderSection)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Sort,
                            contentDescription = "Sort"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        bottomBar = {
            CustomBottomNavigation(
                selectedRoute = Screen.ContactsScreen.route,
                onItemSelected = {
                    navController.navigate(it.route)
                })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Column {
                    SearchView(
                        text = viewModel.queryState.value.text,
                        onValueChange = {
                            viewModel.onEvent(ContactsEvent.ChangeSearchQuery(it))
                        },
                        onCancelClicked = {
                            viewModel.onEvent(ContactsEvent.CancelQuery)
                        }
                    )
                    OrderSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        orderType = state.orderType,
                        onOrderChange = {
                            viewModel.onEvent(ContactsEvent.Order(it))
                        }
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(state.contacts) { contact ->
                    ContactItem(
                        contact = contact,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditContactScreen.route +
                                            "?contactId=${contact.id}"
                                )
                            },
                        onDeleteClick = {
                            viewModel.onEvent(ContactsEvent.DeleteContact(contact))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Contact deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(ContactsEvent.RestoreContact)
                                }
                            }
                        },
                        elevation = 8
                    )
                }
            }
        }
    }
}