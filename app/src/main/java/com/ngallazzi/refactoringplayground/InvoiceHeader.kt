package com.ngallazzi.refactoringplayground

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

data class InvoiceHeader(private val number: Int, private val date: LocalDate) {
    override fun toString(): String {
        return "INVOICE N° ${number}, ${formatter.format(date)}\n\n"
    }

    companion object {
        val formatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE;
    }
}