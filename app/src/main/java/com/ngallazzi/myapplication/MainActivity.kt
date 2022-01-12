package com.ngallazzi.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import com.ngallazzi.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        printInvoiceSheet(Utils.getRandomInvoice(this))
    }

    private fun printInvoiceSheet(invoice: Invoice) {
        printInvoiceHeader(invoice)
        printCustomerDetails(invoice.customer)
        printInvoiceItemsSection(invoice.items, 22.0)
    }

    private fun printInvoiceHeader(invoice: Invoice) {
        val invoiceHeader = "INVOICE N° ${invoice.number}, ${invoice.date}\n\n"
        binding.tvInvoicePreview.text = invoiceHeader
    }

    private fun printCustomerDetails(customer: Customer) {
        var customerDetails = ""
        customerDetails += "CUSTOMER\n"
        customerDetails += "First Name: ${customer.firstName}\nLast Name: ${customer.lastName}\n"
        customerDetails += "Address: ${customer.address}\n"
        binding.tvInvoicePreview.append("$customerDetails\n")
    }


    private fun printInvoiceItemsSection(items: List<InvoiceItem>, vat: Double) {
        binding.tvInvoicePreview.append("QTY \t DESCRIPTION \t UNIT PRICE \t AMOUNT\n")
        for (item in items) {
            binding.tvInvoicePreview.append(item.quantity.toString().padEnd(20))
            binding.tvInvoicePreview.append(
                item.product.description.padEnd(15)
            )
            binding.tvInvoicePreview.append(
                item.product.unitPrice.toDoubleDigitsString().padEnd(20)
            )
            binding.tvInvoicePreview.append(
                (item.product.unitPrice * item.quantity + (item.product.unitPrice * item.quantity * vat/100.0)).toDoubleDigitsString()
                    .plus("\t\n")
            )
        }
    }
}