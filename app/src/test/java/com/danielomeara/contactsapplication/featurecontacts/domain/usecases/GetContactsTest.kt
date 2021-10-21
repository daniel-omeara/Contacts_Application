package com.danielomeara.contactsapplication.featurecontacts.domain.usecases

import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import com.danielomeara.contactsapplication.featurecontacts.domain.repository.ContactRepository
import com.danielomeara.contactsapplication.featurecontacts.domain.util.OrderType
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetContactsTest {
    private lateinit var repository: ContactRepository
    private lateinit var contactsListAscending: List<List<Contact>>
    private lateinit var contactsListDescending: List<List<Contact>>

    @Before
    fun setUp() {
        repository = UseCaseTestUtils.TestRepository()
        contactsListAscending = UseCaseTestUtils.contactsListAscending
        contactsListDescending = UseCaseTestUtils.contactsListDescending
    }

    @Test
    fun givenContact_whenNameBlank_thenThrowInvalidContactException() {
        runBlocking  {
            assert(GetContacts(repository).invoke(OrderType.Descending).toList() == contactsListDescending)
        }
    }

    @Test
    fun givenContact_whenNameBlk_thenThrowInvalidContactException() {
        runBlocking  {
            assert(GetContacts(repository).invoke(OrderType.Ascending).toList() == contactsListAscending)
        }
    }
}