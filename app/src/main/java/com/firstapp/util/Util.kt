package com.firstapp.util

import android.content.Context
import android.widget.Toast

fun showToastLong(context: Context?, msg: String) {
    context?.let {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

}

fun showToastShort(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}