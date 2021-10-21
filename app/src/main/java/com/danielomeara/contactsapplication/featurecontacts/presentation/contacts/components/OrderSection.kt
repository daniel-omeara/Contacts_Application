package com.danielomeara.contactsapplication.featurecontacts.presentation.contacts.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danielomeara.contactsapplication.featurecontacts.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    orderType: OrderType = OrderType.ASCENDING,
    onOrderChange: (OrderType) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        DefaultRadioButton(
            text = "Ascending",
            selected = orderType == OrderType.ASCENDING,
            onSelect = {
                onOrderChange(OrderType.ASCENDING)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        DefaultRadioButton(
            text = "Descending",
            selected = orderType == OrderType.DESCENDING,
            onSelect = { onOrderChange(OrderType.DESCENDING) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}