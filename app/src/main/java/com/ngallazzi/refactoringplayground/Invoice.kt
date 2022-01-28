package com.ngallazzi.refactoringplayground

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime


data class Invoice(
    val number: Int,
    val customer: Customer,
    val date: LocalDate,
    val items: List<InvoiceItem>,
    val printTimeStamp: LocalDateTime? = null,
    val printingAuthor: String? = null
)