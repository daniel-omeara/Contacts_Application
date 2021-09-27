package com.danielomeara.contactsapplication.feature_contacts.presentation.contacts

import com.danielomeara.contactsapplication.feature_contacts.domain.model.Contact
import com.danielomeara.contactsapplication.feature_contacts.domain.util.OrderType

data class ContactsState(
    val contacts: List<Contact> = emptyList(),
    val orderType: OrderType = OrderType.Descending,
    val isOrderSectionVisible: Boolean = false
)