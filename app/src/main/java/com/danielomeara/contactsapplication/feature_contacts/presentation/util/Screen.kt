package com.danielomeara.contactsapplication.feature_contacts.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Contacts
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object CallingScreen: Screen(route = "calling_screen", title = "Keypad", icon = Icons.Rounded.Call)
    object ContactsScreen: Screen(route = "contacts_screen", title = "Contacts", icon = Icons.Rounded.Contacts)
    object AddEditContactScreen: Screen(route = "add_edit_contact_screen")
}
