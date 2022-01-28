package com.ngallazzi.refactoringplayground

import org.threeten.bp.LocalDateTime

class MainActivityController {
    private var printingState: PrintingState = PrintingState.UNDEFINED

    fun setPrintingState(newState: PrintingState) {
        printingState = newState
    }

    fun getPrintingState(): PrintingState {
        return printingState;
    }

    fun addAuthor(invoice: Invoice, author: String): Invoice {
        invoice.printingAuthor = author
        return invoice
    }

    fun addPrintTimeStamp(invoice: Invoice, timeStamp: LocalDateTime): Invoice {
        invoice.printTimeStamp = timeStamp
        return invoice
    }
}