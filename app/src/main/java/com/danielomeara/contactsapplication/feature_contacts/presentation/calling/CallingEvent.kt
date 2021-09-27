package com.danielomeara.contactsapplication.feature_contacts.presentation.calling

sealed class CallingEvent {
    data class ButtonPressed(val number: String): CallingEvent()
    data class ButtonLongPressed(val number: String): CallingEvent()
    object BackspacePressed: CallingEvent()
    data class MakePhoneCall(val phoneNumber: String): CallingEvent()
}
