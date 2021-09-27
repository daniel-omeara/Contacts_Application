package com.danielomeara.contactsapplication.feature_contacts.domain.use_case

import com.danielomeara.contactsapplication.feature_contacts.domain.model.Contact
import com.danielomeara.contactsapplication.feature_contacts.domain.repository.ContactRepository

class DeleteContact(
    private val repository: ContactRepository
) {

    suspend operator fun invoke(contact: Contact): Int? {
        return repository.deleteContact(contact)
    }

}