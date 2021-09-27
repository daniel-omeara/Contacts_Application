package com.danielomeara.contactsapplication.feature_contacts.domain.use_case

import com.danielomeara.contactsapplication.feature_contacts.domain.model.Contact
import com.danielomeara.contactsapplication.feature_contacts.domain.repository.ContactRepository
import com.danielomeara.contactsapplication.feature_contacts.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetContacts(
    private val repository: ContactRepository
) {

    operator fun invoke(
        orderType: OrderType = OrderType.Descending,
        searchQuery: String? = null
    ): Flow<List<Contact>> {
        return if(searchQuery.isNullOrBlank()) repository.getContacts().map { notes ->
            when(orderType) {
                is OrderType.Ascending -> notes.sortedBy { it.name.lowercase() }
                is OrderType.Descending -> notes.sortedByDescending { it.name.lowercase() }
            }
        } else {
            repository.getContactsByQuery(searchQuery).map { notes ->
                when(orderType) {
                    is OrderType.Ascending -> notes.sortedBy { it.name.lowercase() }
                    is OrderType.Descending -> notes.sortedByDescending { it.name.lowercase() }
                }

            }
        }
    }
}