package com.ngallazzi.myapplication

import android.content.Context
import io.kimo.lib.faker.component.text.AddressComponent
import io.kimo.lib.faker.component.text.LoremComponent
import io.kimo.lib.faker.component.text.NameComponent
import org.threeten.bp.LocalDate
import java.text.DecimalFormat
import kotlin.random.Random

object Utils {
    private fun provideRandomCustomer(context: Context): Customer {
        return Customer(
            NameComponent(context).firstName(),
            NameComponent(context).lastName(),
            AddressComponent(context).randomText()
        )
    }

    private fun provideRandomItems(context: Context): List<InvoiceItem> {
        val items = mutableListOf<InvoiceItem>()
        for (i in 1..Random.nextInt(2, 8)) {
            items.add(
                InvoiceItem(
                    Product(
                        "desc-$i",
                        Random.nextDouble()
                    ),
                    Random.nextInt(1, 10)
                )
            )
        }
        return items
    }

    fun getRandomInvoice(context: Context): Invoice {
        return Invoice(
            Random.nextInt(),
            provideRandomCustomer(context),
            LocalDate.now(),
            provideRandomItems(context)
        )
    }
}

fun Double.toDoubleDigitsString(): String {
    val df = DecimalFormat("0.00")
    return df.format(this)
}