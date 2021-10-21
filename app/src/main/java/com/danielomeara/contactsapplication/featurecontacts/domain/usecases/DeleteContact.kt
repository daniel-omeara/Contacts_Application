package com.danielomeara.contactsapplication.featurecontacts.domain.usecases

import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import com.danielomeara.contactsapplication.featurecontacts.domain.repository.ContactRepository

class DeleteContact(
    private val repository: ContactRepository
) {

    suspend operator fun invoke(contact: Contact): Int? {
        return repository.deleteContact(contact)
    }

}