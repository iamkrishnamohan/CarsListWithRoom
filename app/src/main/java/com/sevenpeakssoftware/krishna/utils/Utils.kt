package com.sevenpeakssoftware.krishna.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

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
