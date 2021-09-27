package com.danielomeara.contactsapplication.feature_contacts.domain.use_case

data class ContactsUseCases(
    val getContacts: GetContacts,
    val deleteContact: DeleteContact,
    val addContact: AddContact,
    val getContact: GetContact,
    val formatPhoneNumber: FormatPhoneNumber
)
