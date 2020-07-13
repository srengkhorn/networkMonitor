package com.techkh.networkmonitor

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.techkh.networkmonitor.util.CoroutineUtil
import kotlinx.android.synthetic.main.dialog_warning.*

class NetworkMonitor(private val context: Context, private var listener: WarningListener) {

    private var networkMonitor = NetworkStateMonitor(this.context)

    private lateinit var dialogLayout: View
    lateinit var dialog: AlertDialog

    fun start(title: String = context.getString(R.string.internet_error), message: String = context.getString(R.string.content_warning)) {
        initWaringDialog(title, message)
        initMonitor()
    }

    private fun initWaringDialog(title: String, message: String) {
        dialogLayout = LayoutInflater.from(context).inflate(R.layout.dialog_warning, null)
        val builder = AlertDialog.Builder(context).setView(dialogLayout)
        dialog = builder.show()
        dialog.setCancelable(false)

        dialog.warningTitle.text = title
        dialog.warningMessage.text = message
        dialog.btnCancel.setOnClickListener { listener.onCancel() }
        dialog.btnTryAgain.setOnClickListener { listener.onTryAgain() }
    }

    private fun initMonitor() {
        networkMonitor.result = { isAvailable, type ->
            CoroutineUtil.main {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.WIFI -> {
                                dialog.dismiss()
                            }
                            ConnectionType.CELLULAR -> {
                                dialog.dismiss()
                            }
                            else -> {}
                        }
                    }
                    false -> {
                        dialog.show()
                    }
                }
            }
        }
    }

    fun register() {
        networkMonitor.register()
    }

    fun unregister() {
        networkMonitor.unregister()
    }

    interface WarningListener {
        fun onCancel()
        fun onTryAgain()
    }
}