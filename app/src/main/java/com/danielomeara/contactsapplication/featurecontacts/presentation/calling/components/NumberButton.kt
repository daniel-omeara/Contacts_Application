package com.danielomeara.contactsapplication.featurecontacts.presentation.calling.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.danielomeara.contactsapplication.ui.theme.hintText

@ExperimentalFoundationApi
@Composable
fun NumberButton(
    onNumberClick: () -> Unit,
    headText: String,
    modifier: Modifier = Modifier,
    onNumberLongClick: () -> Unit = {},
    headColor: Color = MaterialTheme.colors.onSurface,
    subColor: Color = MaterialTheme.colors.hintText,
    subText: String = ""
) {

    Column(
        modifier = modifier
            .combinedClickable(
                onClick = onNumberClick,
                onLongClick = onNumberLongClick
            )
    ) {
        Text(
            text = headText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5,
            color = headColor
        )
        Text(
            text = subText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            color = subColor
        )

    }

}