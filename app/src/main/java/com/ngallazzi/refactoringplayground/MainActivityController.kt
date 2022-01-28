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

    fun enrichInvoice(invoice: Invoice, author: String, timeStamp: LocalDateTime): Invoice {
        return invoice.copy(printingAuthor = author, printTimeStamp = timeStamp)
    }
}