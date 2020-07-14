package com.techkh.networkmonitor

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import com.techkh.networkmonitor.util.CoroutineUtil
import kotlinx.android.synthetic.main.dialog_warning.*

class NetworkMonitor(private val context: Context) {

    private var networkMonitor = NetworkStateMonitor(this.context)
    private lateinit var listener: ConnectionListener

    private lateinit var dialogLayout: View
    private lateinit var dialog: AlertDialog

    fun start() {
        initDialog()
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

    @SuppressLint("InflateParams")
    private fun initDialog() {
        dialogLayout = LayoutInflater.from(context).inflate(R.layout.dialog_warning, null)
        val builder = AlertDialog.Builder(context).setView(dialogLayout)
        dialog = builder.show()
        dialog.setCancelable(false)
    }

    fun onDialogActionListener(listener: (DialogListener)) {
        dialog.btnCancel.setOnClickListener { listener.onCancel() }
        dialog.btnTryAgain.setOnClickListener { listener.onTryAgain() }
    }

    private fun setCustomContent(title: String, message: String) {
        dialog.warningTitle.text = title
        dialog.warningMessage.text = message
    }

    fun setBackgroundTitle(colorString: String = "#F57C00") {
        dialog.warningTitle.setBackgroundColor(Color.parseColor(colorString))
    }

    fun hideAlert() {
        dialog.dismiss()
    }

    fun showAlert(title: String = context.getString(R.string.internet_error), message: String = context.getString(R.string.content_warning)) {
        setCustomContent(title, message)
        dialog.show()
    }

    fun register() {
        networkMonitor.register()
    }

    fun unregister() {
        networkMonitor.unregister()
    }

    fun setNetworkListener(listener: ConnectionListener) {
        this.listener = listener
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