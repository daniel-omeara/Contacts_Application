package com.danielomeara.contactsapplication.featurecontacts.domain.usecases

import com.danielomeara.contactsapplication.featurecontacts.domain.model.Contact
import com.danielomeara.contactsapplication.featurecontacts.domain.model.InvalidContactException
import com.danielomeara.contactsapplication.featurecontacts.domain.repository.ContactRepository

class AddContact(
    private val repository: ContactRepository
) {

    @Throws(InvalidContactException::class)
    suspend operator fun invoke(contact: Contact): Long? {
        if(contact.name.isBlank()) {
            throw InvalidContactException("The name of a contact can't be empty.")
        }
        if(contact.phoneNumber.isBlank()) {
            throw InvalidContactException("The phone number of a contact can't be empty.")
        }
        return repository.insertContact(contact)
    }

}