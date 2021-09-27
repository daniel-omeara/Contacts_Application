package com.danielomeara.contactsapplication.feature_contacts.presentation.add_edit_contact.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.danielomeara.contactsapplication.ui.theme.hintText

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
    onFocusChange: (FocusState) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            label = {
                if(!isHintVisible) {
                    Text(hint)
                }
            },
            modifier = modifier
                .onFocusChanged {
                    onFocusChange(it)
                },
            keyboardOptions = keyboardOptions,
        )
        if(isHintVisible) {
            Text(
                text = hint,
                style = textStyle,
                color = MaterialTheme.colors.hintText,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp, top = 8.dp)
            )
        }
    }
}