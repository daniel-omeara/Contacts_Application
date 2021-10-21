package com.danielomeara.contactsapplication.featurecontacts.presentation.calling

data class CallingState(
    val phoneNumber: String = "",
    val permissionGranted: Boolean = false
)