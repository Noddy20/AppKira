package com.nnk.appkira.core.ext

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import com.nnk.appkira.core.logger.Logger

fun String.Companion.empty() = ""

fun Context.copyContentToClipboard(
    label: String,
    content: String,
) {
    try {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, content)
        clipboard.setPrimaryClip(clip)
    } catch (e: Exception) {
        Logger.e("Error copying to clipboard", e)
    }
}

fun Context.toast(message: String) {
    Toast
        .makeText(
            this,
            message,
            Toast.LENGTH_SHORT,
        ).show()
}
