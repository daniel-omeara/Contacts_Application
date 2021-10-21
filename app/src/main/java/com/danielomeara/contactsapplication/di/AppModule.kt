package com.danielomeara.contactsapplication.di

import android.app.Application
import androidx.room.Room
import com.danielomeara.contactsapplication.featurecontacts.data.datasource.ContactDatabase
import com.danielomeara.contactsapplication.featurecontacts.data.repository.ContactRepositoryImpl
import com.danielomeara.contactsapplication.featurecontacts.domain.repository.ContactRepository
import com.danielomeara.contactsapplication.featurecontacts.domain.usecases.*
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
    fun provideFormatPhoneNumberUseCase(): FormatPhoneNumber {
        return FormatPhoneNumber()
    }

    @Provides
    @Singleton
    fun provideGetContactUseCase(repository: ContactRepository): GetContact {
        return GetContact(repository)
    }

    @Provides
    @Singleton
    fun provideAddContactUseCase(repository: ContactRepository): AddContact {
        return AddContact(repository)
    }

    @Provides
    @Singleton
    fun provideGetContactsUseCase(repository: ContactRepository): GetContacts {
        return GetContacts(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteContactUseCase(repository: ContactRepository): DeleteContact {
        return DeleteContact(repository)
    }

}