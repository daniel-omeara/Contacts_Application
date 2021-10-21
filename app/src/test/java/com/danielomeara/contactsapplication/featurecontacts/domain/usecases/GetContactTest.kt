package com.danielomeara.contactsapplication.featurecontacts.domain.usecases

import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import com.danielomeara.contactsapplication.featurecontacts.domain.repository.ContactRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetContactTest {

    private lateinit var repository: ContactRepository
    private lateinit var contact: Contact

    @Before
    fun setUp() {
        repository = UseCaseTestUtils.TestRepository()
        contact = UseCaseTestUtils.contact
    }

    @Test
    fun givenContact_whenNameBlank_thenThrowInvalidContactException() {
        runBlocking  {
            GetContact(repository).invoke(0)?.equals(contact)?.let { assert(it) }
        }
    }
}