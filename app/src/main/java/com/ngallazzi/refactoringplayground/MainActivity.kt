package com.ngallazzi.refactoringplayground

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import com.ngallazzi.refactoringplayground.databinding.ActivityMainBinding
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val controller = MainActivityController()
    private lateinit var printingHelper: PrintingHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        printingHelper = PrintingHelper(binding.tvInvoicePreview)

        controller.setPrintingState(PrintingState.IDLE)
        val invoice = Utils.getRandomInvoice(this, resources.configuration.locale)

        val invoiceHeader = InvoiceHeader(invoice.number, invoice.date)
        printingHelper.printInvoiceHeader(invoiceHeader)
        controller.setPrintingState(PrintingState.IN_PROGRESS)

        printCustomerDetails(invoice.customer)
        printInvoiceItemsSection(invoice.items, 22.0)
        controller.setPrintingState(PrintingState.DONE)

        Log.v(TAG, "Print state: ${controller.getPrintingState().name}")
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

    companion object {
        const val TAG = "MainActivity"
    }
}