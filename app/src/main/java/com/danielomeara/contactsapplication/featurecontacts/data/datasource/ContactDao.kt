package com.danielomeara.contactsapplication.featurecontacts.data.datasource

import androidx.room.*
import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact")
    fun getContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contact WHERE contact.name LIKE '%' || :searchQuery || '%'")
    fun getContactsByQuery(searchQuery: String): Flow<List<Contact>>

    @Query("SELECT * FROM contact WHERE id = :id")
    suspend fun getContactById(id: Int): Contact?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact): Long?

    @Delete
    suspend fun deleteContact(contact: Contact): Int?

}