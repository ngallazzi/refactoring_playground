package com.ngallazzi.myapplication

import android.content.Context
import io.kimo.lib.faker.component.text.AddressComponent
import io.kimo.lib.faker.component.text.NameComponent
import org.threeten.bp.LocalDate
import kotlin.random.Random

object Utils {
    private fun provideRandomCustomer(context: Context): Customer {
        return Customer(
            NameComponent(context).firstName(),
            NameComponent(context).lastName(),
            AddressComponent(context).randomText()
        )
    }

    fun getRandomInvoice(context: Context): Invoice {
        return Invoice(
            Random.nextInt(),
            provideRandomCustomer(context),
            LocalDate.now(),
            Random.nextDouble()
        )
    }
}