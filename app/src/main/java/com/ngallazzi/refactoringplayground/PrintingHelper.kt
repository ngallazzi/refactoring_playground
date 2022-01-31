package com.ngallazzi.refactoringplayground

import android.annotation.SuppressLint
import android.widget.TextView
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

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

    fun printInvoiceItemsSection(items: List<InvoiceItem>, vat: Double) {
        destination.append("QTY \t DESCRIPTION \t UNIT PRICE \t AMOUNT\n")
        for (item in items) {
            destination.append(item.quantity.toString().padEnd(20))
            destination.append(
                item.product.description.padEnd(15)
            )
            destination.append(
                item.product.unitPrice.toDoubleDigitsString().padEnd(20)
            )
            val quantityPrice = item.product.unitPrice * item.quantity;
            val vatPercentage = vat / 100.0
            destination.append(
                (quantityPrice + (quantityPrice * vatPercentage)).toDoubleDigitsString()
                    .plus("\t\n")
            )
        }
    }

    @SuppressLint("SetTextI18n")
    fun printMustBePayedByDisclaimer(invoice: Invoice) {
        val daysToAdd = 30L
        destination.text = ("Pay by: " + invoice.date.plusDays(daysToAdd).dayOfMonth + "/"
                + invoice.date.month + "/" + invoice.date.year) + "\n" +
                " COORDINATES: IBAN: IT40L0200805364000030085962 " +
                "SWIFT: UNCRITMMORR Unicredit Banca\n" +
                "\n "
    }
}