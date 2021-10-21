package com.danielomeara.contactsapplication.featurecontacts.domain.usecases

import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import com.danielomeara.contactsapplication.featurecontacts.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

object UseCaseTestUtils {

    val contact: Contact = Contact(name = "Daniel", phoneNumber = "1234567890")
    val contactsList: MutableList<List<Contact>> = mutableListOf(
        mutableListOf(
            Contact(name = "Daniel", phoneNumber = "1234567890"),
            Contact(name = "Brian", phoneNumber = "1234567890")
        )
    )
    val contactsListAscending: List<List<Contact>> = contactsList.map { notes ->
        notes.sortedBy { it.name.lowercase() }
    }
    val contactsListDescending: List<List<Contact>> = contactsList.map { notes ->
        notes.sortedByDescending { it.name.lowercase() }
    }

    class TestRepository: ContactRepository {
        override fun getContacts(): Flow<List<Contact>> {
            return contactsList.asFlow()
        }

        override fun getContactsByQuery(searchQuery: String): Flow<List<Contact>> {
            TODO("Not yet implemented")
        }

        override suspend fun getContactById(id: Int): Contact {
            return contact
        }

        override suspend fun insertContact(contact: Contact): Long {
            return 0
        }

        override suspend fun deleteContact(contact: Contact): Int {
            return 0
        }

    }
}