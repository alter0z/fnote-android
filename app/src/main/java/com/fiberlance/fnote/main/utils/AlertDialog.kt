package com.fiberlance.fnote.main.utils

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AlertDialog(activity: AppCompatActivity) {
    private var builder: MaterialAlertDialogBuilder? = null
    init {
        builder = MaterialAlertDialogBuilder(activity)
    }

    fun successAlert(message: String) {
        builder?.setTitle("Success!")
        builder?.setMessage(message)
        builder?.setNeutralButton("Close") { _, _ -> }
        val dialog = builder?.create()
        dialog?.show()
    }

    fun warningAlert(message: String) {
        builder?.setTitle("Warning!")
        builder?.setMessage(message)
        builder?.setNeutralButton("Close") { _, _ -> }
        val dialog = builder?.create()
        dialog?.show()
    }

    fun errorAlert(message: String) {
        builder?.setTitle("Error!")
        builder?.setMessage(message)
        builder?.setNeutralButton("Close") { _, _ -> }
        val dialog = builder?.create()
        dialog?.show()
    }

//    fun logoutAlert(callback: (Boolean) -> Unit) {
//        builder?.setTitle("Log out?")
//        builder?.setMessage("Are you sure you want to logout of FaceGuard?")
//        builder?.setPositiveButton("No") { _, _ -> callback(false) }
//        builder?.setNegativeButton("Yes") { _, _ -> callback(true) }
//
//        val dialog = builder?.create()
//        dialog?.show()
//    }
//
    fun successWithCallback(message: String, callback: (Boolean) -> Unit) {
        builder?.setTitle("Success!")
        builder?.setMessage(message)
        builder?.setNeutralButton("Close") { _, _ -> callback(true) }

        val dialog = builder?.create()
        dialog?.show()
    }
//
//    fun warningWithCallback(message: String, callback: (Boolean) -> Unit) {
//        builder?.setTitle("Warning!")
//        builder?.setMessage(message)
//        builder?.setNeutralButton("Close") { _, _ -> callback(true) }
//
//        val dialog = builder?.create()
//        dialog?.show()
//    }
//
//    fun errorWithCallback(message: String, callback: (Boolean) -> Unit) {
//        builder?.setTitle("Error!")
//        builder?.setMessage(message)
//        builder?.setNeutralButton("Close") { _, _ -> callback(true) }
//
//        val dialog = builder?.create()
//        dialog?.show()
//    }
//
//    fun historyDeleteAlert(callback: (Boolean) -> Unit) {
//        builder?.setTitle("Delete?")
//        builder?.setMessage("Are you sure you want to delete this history?")
//        builder?.setPositiveButton("No") { _, _ -> callback(false) }
//        builder?.setNegativeButton("Yes") { _, _ -> callback(true) }
//
//        val dialog = builder?.create()
//        dialog?.show()
//    }
}