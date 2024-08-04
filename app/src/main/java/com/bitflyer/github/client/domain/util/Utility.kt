package com.bitflyer.github.client.domain.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object Utility {

    fun hideKeyboard(context: Context, target: View) {
        val inputMethodManager = getInputMethodManager(context)
        inputMethodManager.hideSoftInputFromWindow(target.windowToken, 0)
    }

    private fun getInputMethodManager(context: Context): InputMethodManager {
        return context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
}
