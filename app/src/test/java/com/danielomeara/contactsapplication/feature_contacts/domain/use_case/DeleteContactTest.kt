package com.danielomeara.contactsapplication.feature_contacts.domain.use_case

import com.danielomeara.contactsapplication.feature_contacts.domain.model.Contact
import com.danielomeara.contactsapplication.feature_contacts.domain.repository.ContactRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteContactTest {

    private lateinit var repository: ContactRepository
    private lateinit var contact: Contact

    @Before
    fun setUp() {
        repository = UseCaseTestUtils.TestRepository()
        contact = Contact(name = "Daniel", phoneNumber = "1234567890")
    }

    @Test
    fun givenContact_whenNameBlank_thenThrowInvalidContactException() {
        runBlocking  {
            assert(DeleteContact(repository).invoke(contact) == 0)
        }
    }

}