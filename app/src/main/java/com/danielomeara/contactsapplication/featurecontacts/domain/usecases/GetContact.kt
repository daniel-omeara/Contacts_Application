package com.danielomeara.contactsapplication.featurecontacts.domain.usecases

import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import com.danielomeara.contactsapplication.featurecontacts.domain.repository.ContactRepository

class GetContact(
    private val repository: ContactRepository
) {

    suspend operator fun invoke(id: Int): Contact? {
        return repository.getContactById(id)
    }
}