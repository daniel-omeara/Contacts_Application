package com.danielomeara.contactsapplication.featurecontacts.domain.usecases

import java.util.regex.Matcher
import java.util.regex.Pattern


class FormatPhoneNumber {

    operator fun invoke(phoneNumber: String): String {
        val regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$"

        val pattern: Pattern = Pattern.compile(regex)

        val matcher: Matcher = pattern.matcher(phoneNumber)
        return matcher.replaceFirst("($1) $2-$3")
    }

}