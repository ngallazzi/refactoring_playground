package com.ngallazzi.refactoringplayground

import android.widget.TextView

class PrintingHelper(private val destination: TextView) {
    fun printInvoiceHeader(header: InvoiceHeader) {
        destination.text = header.toString()
    }
}