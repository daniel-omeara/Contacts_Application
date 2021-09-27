package com.danielomeara.contactsapplication.feature_contacts.domain.use_case

import com.danielomeara.contactsapplication.feature_contacts.domain.model.Contact
import com.danielomeara.contactsapplication.feature_contacts.domain.model.InvalidContactException
import com.danielomeara.contactsapplication.feature_contacts.domain.repository.ContactRepository

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