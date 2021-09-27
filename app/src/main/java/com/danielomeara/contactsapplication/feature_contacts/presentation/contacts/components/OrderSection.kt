package com.danielomeara.contactsapplication.feature_contacts.presentation.contacts.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danielomeara.contactsapplication.feature_contacts.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    orderType: OrderType = OrderType.Ascending,
    onOrderChange: (OrderType) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        DefaultRadioButton(
            text = "Ascending",
            selected = orderType is OrderType.Ascending,
            onSelect = {
                onOrderChange(OrderType.Ascending)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        DefaultRadioButton(
            text = "Descending",
            selected = orderType is OrderType.Descending,
            onSelect = { onOrderChange(OrderType.Descending) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}