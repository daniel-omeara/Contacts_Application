package com.danielomeara.contactsapplication.featurecontacts.domain.usecases

import junit.framework.Assert.assertEquals
import org.junit.Test

class FormatPhoneNumberTest {

    @Test
    fun test() {
        assertEquals(FormatPhoneNumber().invoke("1234567890"), "(123) 456-7890")
    }

}