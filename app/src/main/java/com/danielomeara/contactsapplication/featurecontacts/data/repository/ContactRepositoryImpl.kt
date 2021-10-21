package com.danielomeara.contactsapplication.featurecontacts.data.repository

import com.danielomeara.contactsapplication.featurecontacts.data.datasource.ContactDao
import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import com.danielomeara.contactsapplication.featurecontacts.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl(
    private val dao: ContactDao
) : ContactRepository {

    override fun getContacts(): Flow<List<Contact>> {
        return dao.getContacts()
    }

    override fun getContactsByQuery(searchQuery: String): Flow<List<Contact>> {
        return dao.getContactsByQuery(searchQuery)
    }

    override suspend fun getContactById(id: Int): Contact? {
        return dao.getContactById(id)
    }

    override suspend fun insertContact(contact: Contact): Long? {
        return dao.insertContact(contact)
    }

    override suspend fun deleteContact(contact: Contact): Int? {
        return dao.deleteContact(contact)
    }

}