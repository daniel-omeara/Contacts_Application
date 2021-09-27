package com.danielomeara.contactsapplication.feature_contacts.presentation.calling

data class CallingState(
    val phoneNumber: String = "",
    val permissionGranted: Boolean = false
)