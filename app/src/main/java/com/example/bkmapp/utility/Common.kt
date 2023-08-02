package com.example.bkmapp.utility

import java.text.NumberFormat
import java.util.*

fun formatCurrency(amount: Int): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID")) // Specify the locale for Indonesian Rupiah
    return formatter.format(amount.toLong())
}