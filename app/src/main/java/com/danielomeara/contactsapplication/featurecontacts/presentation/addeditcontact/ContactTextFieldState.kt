package com.danielomeara.contactsapplication.featurecontacts.presentation.addeditcontact

data class ContactTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
