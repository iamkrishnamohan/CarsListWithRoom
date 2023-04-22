package com.sevenpeakssoftware.krishna.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun View.showSnackBar(msg: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, msg, length).show()
}


//show and hide visibility
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}


fun convertDateToDifferentFormat(date: String): String? {
    var convertedDate: String? = ""
    try {
        val from: DateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH) // wanted format
        val currentDate: Date = from.parse(date) as Date
        println("current Date is $currentDate")
        val to: DateFormat =
            SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.ENGLISH) // current format
        convertedDate = to.format(currentDate)

        println(convertedDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return convertedDate

}