package com.ngallazzi.refactoringplayground

import android.widget.TextView

class PrintingHelper(private val destination: TextView) {
    fun printInvoiceHeader(header: InvoiceHeader) {
        destination.text = header.toString()
    }

    fun printCustomerDetails(customer: Customer) {
        var customerDetails = ""
        customerDetails += "CUSTOMER\n"
        customerDetails += "First Name: ${customer.firstName}\nLast Name: ${customer.lastName}\n"
        customerDetails += "Address: ${customer.address}\n"
        destination.append("$customerDetails\n")
    }
}