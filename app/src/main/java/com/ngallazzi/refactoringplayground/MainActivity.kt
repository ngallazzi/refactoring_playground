package com.ngallazzi.refactoringplayground

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.ConfigurationCompat
import com.jakewharton.threetenabp.AndroidThreeTen
import com.ngallazzi.refactoringplayground.databinding.ActivityMainBinding
import org.threeten.bp.LocalDateTime

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
        val local = ConfigurationCompat.getLocales(this.resources.configuration)[0]
        var invoice = Utils.getRandomInvoice(this, local)
        invoice = controller.enrichInvoice(invoice, "Nicola", LocalDateTime.now())

        val invoiceHeader = InvoiceHeader(invoice.number, invoice.date)
        printingHelper.printInvoiceHeader(invoiceHeader)
        controller.setPrintingState(PrintingState.IN_PROGRESS)

        printingHelper.printCustomerDetails(invoice.customer)

        printingHelper.printInvoiceItemsSection(invoice.items, 22.0)
        printingHelper.printMustBePayedByDisclaimer(invoice)
        controller.setPrintingState(PrintingState.DONE)

        Log.v(TAG, "Print state: ${controller.getPrintingState().name}")
    }

    companion object {
        const val TAG = "MainActivity"
    }
}