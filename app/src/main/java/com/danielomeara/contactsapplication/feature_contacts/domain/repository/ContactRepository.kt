package com.danielomeara.contactsapplication.feature_contacts.domain.repository

import com.danielomeara.contactsapplication.feature_contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    fun getContacts(): Flow<List<Contact>>

    fun getContactsByQuery(searchQuery: String): Flow<List<Contact>>

    suspend fun getContactById(id: Int): Contact?

    suspend fun insertContact(contact: Contact): Long?

    suspend fun deleteContact(contact: Contact): Int?

}