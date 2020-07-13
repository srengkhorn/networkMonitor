package com.techkh.networkmonitor

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import com.techkh.networkmonitor.util.CoroutineUtil
import kotlinx.android.synthetic.main.dialog_warning.*

class NetworkMonitor(private val context: Context, private var listener: ConnectionListener) {

    private var networkMonitor = NetworkStateMonitor(this.context)

    private lateinit var dialogLayout: View
    lateinit var dialog: AlertDialog

    fun start(
        title: String = context.getString(R.string.internet_error),
        message: String = context.getString(R.string.content_warning)) {

        initDialog(title, message)
        initMonitor()
    }

    private fun initMonitor() {
        networkMonitor.result = { isAvailable, type ->
            CoroutineUtil.main {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.WIFI -> {
                                listener.isConnected()
                            }
                            ConnectionType.CELLULAR -> {
                                listener.isConnected()
                            }
                            else -> {}
                        }
                    }
                    false -> {
                        listener.isNotConnected()
                    }
                }
            }
        }
    }

    private fun initDialog(title: String, message: String) {
        dialogLayout = LayoutInflater.from(context).inflate(R.layout.dialog_warning, null)
        val builder = AlertDialog.Builder(context).setView(dialogLayout)
        dialog = builder.show()
        dialog.setCancelable(false)
        dialog.warningTitle.text = title
        dialog.warningMessage.text = message
    }

    fun onDialogActionListener(listener: (DialogListener)) {
        dialog.btnCancel.setOnClickListener { listener.onCancel() }
        dialog.btnTryAgain.setOnClickListener { listener.onTryAgain() }
    }

    fun hideAlert() {
        dialog.dismiss()
    }

    fun showAlert() {
        dialog.show()
    }

    fun register() {
        networkMonitor.register()
    }

    fun unregister() {
        networkMonitor.unregister()
    }

    interface ConnectionListener {
        fun isConnected()
        fun isNotConnected()
    }

    interface DialogListener {
        fun onCancel()
        fun onTryAgain()
    }
}