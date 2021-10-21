package com.danielomeara.contactsapplication.featurecontacts.presentation.contacts

import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import com.danielomeara.contactsapplication.featurecontacts.domain.util.OrderType

sealed class ContactsEvent {
    data class Order(val orderType: OrderType): ContactsEvent()
    data class DeleteContact(val contact: Contact): ContactsEvent()
    data class ChangeSearchQuery(val searchQuery: String): ContactsEvent()
    object RestoreContact: ContactsEvent()
    object ToggleOrderSection: ContactsEvent()
    object CancelQuery: ContactsEvent()
}

