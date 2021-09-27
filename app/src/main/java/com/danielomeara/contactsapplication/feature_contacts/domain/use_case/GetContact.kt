package com.danielomeara.contactsapplication.feature_contacts.domain.use_case

import com.danielomeara.contactsapplication.feature_contacts.domain.model.Contact
import com.danielomeara.contactsapplication.feature_contacts.domain.repository.ContactRepository

class GetContact(
    private val repository: ContactRepository
) {

    suspend operator fun invoke(id: Int): Contact? {
        return repository.getContactById(id)
    }
}