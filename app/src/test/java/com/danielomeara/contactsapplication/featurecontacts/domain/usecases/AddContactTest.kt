package com.danielomeara.contactsapplication.featurecontacts.domain.usecases

import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import com.danielomeara.contactsapplication.featurecontacts.domain.model.InvalidContactException
import com.danielomeara.contactsapplication.featurecontacts.domain.repository.ContactRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddContactTest {

    private lateinit var repository: ContactRepository

    @Before
    fun setUp() {
        repository = UseCaseTestUtils.TestRepository()
    }

    @Test
    fun givenContact_whenNameBlank_thenThrowInvalidContactException() {
        runBlocking  {
            try {
                AddContact(repository).invoke(Contact(name = "", phoneNumber = "1234567890"))
            } catch (e: InvalidContactException) {
                assert(e.message.equals("The name of a contact can't be empty."))
            }
        }
    }

    @Test
    fun givenContact_whenPhoneNumberBlank_thenThrowInvalidContactException() {
        runBlocking  {
            try {
                AddContact(repository).invoke(Contact(name = "Daniel", phoneNumber = ""))
            } catch (e: InvalidContactException) {
                assert(e.message.equals("The phone number of a contact can't be empty."))
            }
        }
    }

    @Test
    fun givenContact_whenValid_thenNoteInserted() {
        runBlocking  {
            try {
                assert(AddContact(repository).invoke(Contact(name = "Daniel", phoneNumber = "1234567890")) == 0L)
            } catch (e: InvalidContactException) {
            }
        }
    }

}