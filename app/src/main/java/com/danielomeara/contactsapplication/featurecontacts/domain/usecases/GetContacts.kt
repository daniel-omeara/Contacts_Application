package com.danielomeara.contactsapplication.featurecontacts.domain.usecases

import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import com.danielomeara.contactsapplication.featurecontacts.domain.repository.ContactRepository
import com.danielomeara.contactsapplication.featurecontacts.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetContacts(
    private val repository: ContactRepository
) {

    operator fun invoke(
        orderType: OrderType = OrderType.DESCENDING,
        searchQuery: String? = null
    ): Flow<List<Contact>> {
        return if(searchQuery.isNullOrBlank()) repository.getContacts().map { notes ->
            when(orderType) {
                OrderType.ASCENDING -> notes.sortedBy { it.name.lowercase() }
                OrderType.DESCENDING -> notes.sortedByDescending { it.name.lowercase() }
            }
        } else {
            repository.getContactsByQuery(searchQuery).map { notes ->
                when(orderType) {
                    OrderType.ASCENDING -> notes.sortedBy { it.name.lowercase() }
                    OrderType.DESCENDING -> notes.sortedByDescending { it.name.lowercase() }
                }

            }
        }
    }
}