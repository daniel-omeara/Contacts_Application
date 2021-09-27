package com.danielomeara.contactsapplication.feature_contacts.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danielomeara.contactsapplication.feature_contacts.domain.model.Contact

@Database(
    entities = [Contact::class],
    version = 1
)
abstract class ContactDatabase : RoomDatabase() {

    abstract val contactDao: ContactDao

    companion object {
        const val DATABASE_NAME = "contacts_db"
    }

}