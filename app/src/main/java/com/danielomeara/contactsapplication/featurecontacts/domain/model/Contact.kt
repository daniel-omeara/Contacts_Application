package com.danielomeara.contactsapplication.featurecontacts.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val phoneNumber: String
)

class InvalidContactException(message: String): Exception(message)
