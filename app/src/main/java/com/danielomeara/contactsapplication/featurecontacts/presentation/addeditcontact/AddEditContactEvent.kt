package com.danielomeara.contactsapplication.featurecontacts.presentation.addeditcontact

import androidx.compose.ui.focus.FocusState

sealed class AddEditContactEvent {
    data class EnteredName(val value: String): AddEditContactEvent()
    data class ChangeNameFocus(val focusState: FocusState): AddEditContactEvent()
    data class EnteredPhoneNumber(val value: String): AddEditContactEvent()
    data class ChangePhoneNumberFocus(val focusState: FocusState): AddEditContactEvent()
    data class MakePhoneCall(val phoneNumber: String): AddEditContactEvent()
    object SaveContact: AddEditContactEvent()
}