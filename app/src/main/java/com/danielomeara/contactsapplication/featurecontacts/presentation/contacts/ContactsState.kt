package com.danielomeara.contactsapplication.featurecontacts.presentation.contacts

import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import com.danielomeara.contactsapplication.featurecontacts.domain.util.OrderType

data class ContactsState(
    val contacts: List<Contact> = emptyList(),
    val orderType: OrderType = OrderType.DESCENDING,
    val isOrderSectionVisible: Boolean = false
)