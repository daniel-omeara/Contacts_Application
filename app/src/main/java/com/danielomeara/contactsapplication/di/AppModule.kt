package com.danielomeara.contactsapplication.di

import android.app.Application
import androidx.room.Room
import com.danielomeara.contactsapplication.feature_contacts.data.data_source.ContactDatabase
import com.danielomeara.contactsapplication.feature_contacts.data.repository.ContactRepositoryImpl
import com.danielomeara.contactsapplication.feature_contacts.domain.repository.ContactRepository
import com.danielomeara.contactsapplication.feature_contacts.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContactDatabase(app: Application): ContactDatabase {
        return Room.databaseBuilder(
            app,
            ContactDatabase::class.java,
            ContactDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideContactRepository(db: ContactDatabase): ContactRepository {
        return ContactRepositoryImpl(db.contactDao)
    }

    @Provides
    @Singleton
    fun provideContactsUseCases(repository: ContactRepository): ContactsUseCases {
        return ContactsUseCases(
            getContacts = GetContacts(repository),
            deleteContact = DeleteContact(repository),
            addContact = AddContact(repository),
            getContact = GetContact(repository),
            formatPhoneNumber = FormatPhoneNumber()
        )
    }

}