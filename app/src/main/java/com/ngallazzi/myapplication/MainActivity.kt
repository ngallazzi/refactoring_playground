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
        // print customer details
        var customerDetails = ""
        customerDetails += "CUSTOMER\n"
        customerDetails += "First Name: ${invoice.customer.firstName}\nLast Name: ${invoice.customer.lastName}\n"
        customerDetails += "Address: ${invoice.customer.address}\n"
        binding.tvInvoicePreview.append(customerDetails)
    }

    private fun printInvoiceHeader(invoice: Invoice) {
        val invoiceHeader = "INVOICE N° ${invoice.number}, ${invoice.date}\n\n"
        binding.tvInvoicePreview.text = invoiceHeader
    }
}