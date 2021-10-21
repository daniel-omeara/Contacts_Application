package com.danielomeara.contactsapplication.featurecontacts.presentation.contacts.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun SearchView(
    text: String,
    onValueChange: (String) -> Unit,
    onCancelClicked: () -> Unit
) {
    Box(
        modifier = Modifier
    ) {
        TextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp),
                    tint = MaterialTheme.colors.onPrimary
                )
            },
            trailingIcon = {
                if (text.isNotBlank()) {
                    IconButton(
                        onClick = onCancelClicked
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Cancel query",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp),
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }
            },
            singleLine = true,
            shape = RectangleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onPrimary,
                cursorColor = MaterialTheme.colors.onPrimary,
                leadingIconColor = MaterialTheme.colors.onPrimary,
                trailingIconColor = MaterialTheme.colors.onPrimary,
                backgroundColor = MaterialTheme.colors.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}