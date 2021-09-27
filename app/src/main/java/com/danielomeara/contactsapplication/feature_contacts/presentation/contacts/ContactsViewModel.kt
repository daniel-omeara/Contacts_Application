package com.danielomeara.contactsapplication.feature_contacts.presentation.contacts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielomeara.contactsapplication.feature_contacts.domain.model.Contact
import com.danielomeara.contactsapplication.feature_contacts.domain.use_case.ContactsUseCases
import com.danielomeara.contactsapplication.feature_contacts.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contactsUseCases: ContactsUseCases
): ViewModel() {

    private val _state = mutableStateOf(ContactsState())
    val state: State<ContactsState> = _state

    private val _queryState = mutableStateOf(SearchQueryTextFieldState())
    val queryState: State<SearchQueryTextFieldState> = _queryState

    private var recentlyDeletedContact: Contact? = null

    private var getContactsJob: Job? = null

    init {
        getContacts(OrderType.Ascending)
    }

    fun onEvent(event: ContactsEvent) {
        when(event) {
            is ContactsEvent.Order -> {
                if(state.value.orderType == event.orderType) {
                    return
                }
                getContacts(event.orderType)
            }
            is ContactsEvent.CancelQuery -> {
                _queryState.value = queryState.value.copy(text = "")
                getContacts(state.value.orderType)
            }
            is ContactsEvent.ChangeSearchQuery -> {
                if(queryState.value.text == event.searchQuery) {
                    return
                } else {
                    _queryState.value = queryState.value.copy(text = event.searchQuery)
                    getContacts(state.value.orderType, event.searchQuery)
                }
            }
            is ContactsEvent.DeleteContact -> {
                viewModelScope.launch {
                    contactsUseCases.deleteContact(event.contact)
                    recentlyDeletedContact = event.contact
                }
            }
            is ContactsEvent.RestoreContact -> {
                viewModelScope.launch {
                    contactsUseCases.addContact(recentlyDeletedContact ?: return@launch)
                    recentlyDeletedContact = null
                }
            }
            is ContactsEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getContacts(orderType: OrderType, searchQuery: String? = null) {
        getContactsJob?.cancel()
        getContactsJob = contactsUseCases.getContacts(orderType, searchQuery)
            .onEach { contacts ->
                _state.value = state.value.copy(
                    contacts = contacts,
                    orderType = orderType
                )
            }
            .launchIn(viewModelScope)
    }

}