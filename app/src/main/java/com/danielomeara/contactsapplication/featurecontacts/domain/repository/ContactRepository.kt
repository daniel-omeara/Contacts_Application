package com.danielomeara.contactsapplication.featurecontacts.domain.repository

import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    fun getContacts(): Flow<List<Contact>>

    fun getContactsByQuery(searchQuery: String): Flow<List<Contact>>

    suspend fun getContactById(id: Int): Contact?

    suspend fun insertContact(contact: Contact): Long?

    suspend fun deleteContact(contact: Contact): Int?

}