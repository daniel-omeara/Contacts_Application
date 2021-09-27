package com.danielomeara.contactsapplication.feature_contacts.presentation.util

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.danielomeara.contactsapplication.ui.theme.bottomNavIcons

@Composable
fun CustomBottomNavigation(
    selectedRoute: String,
    onItemSelected: (Screen) -> Unit
) {
    val items = listOf(
        Screen.CallingScreen,
        Screen.ContactsScreen
    )

    BottomNavigation {
        items.forEach { screen ->
            val isSelected = screen.route == selectedRoute

            BottomNavigationItem(
                icon = {
                    screen.icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = screen.title
                        )
                    }
                },
                label = { screen.title?.let { Text(text = it) } },
                selected = isSelected,
                onClick = {
                    onItemSelected(screen)
                },
                selectedContentColor = MaterialTheme.colors.bottomNavIcons,
                unselectedContentColor = MaterialTheme.colors.onSurface,
                modifier = Modifier.background(MaterialTheme.colors.surface)
            )
        }
    }
}