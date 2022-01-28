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
        return invoice.copy(printingAuthor = author)
    }

    fun addPrintTimeStamp(invoice: Invoice, timeStamp: LocalDateTime): Invoice {
        return invoice.copy(printTimeStamp = timeStamp)
    }
}