package com.ngallazzi.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import com.ngallazzi.myapplication.databinding.ActivityMainBinding
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        printInvoiceSheet(Utils.getRandomInvoice(this, resources.configuration.locale))
    }

    private fun printInvoiceSheet(invoice: Invoice) {
        printInvoiceHeader(
            invoiceNumber = invoice.number,
            invoiceDate = invoice.date,
            format = DateTimeFormatter.ISO_DATE
        )
        // print customer details
        binding.tvInvoicePreview.append(
            "First Name: ${invoice.customer.firstName}" +
                    "\nLast Name: ${invoice.customer.lastName}\n"
        )
        binding.tvInvoicePreview.append("Address: ${invoice.customer.address}\n");
        printCustomerDetails(invoice.customer)
        printInvoiceItemsSection(invoice.items, 22.0)
    }

    private fun printInvoiceHeader(
        invoiceNumber: Int,
        invoiceDate: LocalDate,
        format: DateTimeFormatter
    ) {
        val invoiceHeader =
            "INVOICE N° ${invoiceNumber}, ${invoiceDate.format(format)}\n\n"
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
            val quantityPrice = item.product.unitPrice * item.quantity;
            val vatPercentage = vat / 100.0
            binding.tvInvoicePreview.append(
                (quantityPrice + (quantityPrice * vatPercentage)).toDoubleDigitsString()
                    .plus("\t\n")
            )
        }
    }
}