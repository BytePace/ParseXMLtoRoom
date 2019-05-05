package com.bytepace.ipr.activity

import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

abstract class BaseActivity: AppCompatActivity() {

    protected fun showConfirmationAlert(message: String, title: String = "", callback: () -> Unit) {
        doAsync {
            val dialogBuilder = AlertDialog.Builder(this@BaseActivity)
            if (title.isNotEmpty()) {
                dialogBuilder.setTitle(title).setMessage(message)
            } else {
                dialogBuilder.setTitle(message)
            }
            dialogBuilder.setPositiveButton(android.R.string.yes) { dialog, _ ->
                callback()
                dialog.dismiss()
            }
            dialogBuilder.setNegativeButton(android.R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
            dialogBuilder.setCancelable(true)
            uiThread {
                dialogBuilder.create().show()
            }
        }
    }

    protected fun showConfirmationAlert(messageID: Int, title: String = "", callback: () -> Unit) {
        showConfirmationAlert(getString(messageID), title, callback)
    }

    protected fun showConfirmationAlert(messageID: Int, titleID: Int = -1, callback: () -> Unit) {
        if (titleID == -1) {
            showConfirmationAlert(getString(messageID), "", callback)
        } else {
            showConfirmationAlert(getString(messageID), getString(titleID), callback)
        }
    }

    protected fun showConfirmationAlert(message: String, titleID: Int = -1, callback: () -> Unit) {
        if (titleID == -1) {
            showConfirmationAlert(message, "", callback)
        } else {
            showConfirmationAlert(message, getString(titleID), callback)
        }
    }
}