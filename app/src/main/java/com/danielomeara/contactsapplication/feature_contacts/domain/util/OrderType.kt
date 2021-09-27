package com.danielomeara.contactsapplication.feature_contacts.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}