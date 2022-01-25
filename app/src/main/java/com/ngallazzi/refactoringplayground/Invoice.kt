package com.ngallazzi.refactoringplayground

import org.threeten.bp.LocalDate


data class Invoice(
    val number: Int,
    val customer: Customer,
    val date: LocalDate,
    val items: List<InvoiceItem>
)