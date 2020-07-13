package com.techkh.networkmonitor

import android.content.Context
import com.techkh.networkmonitor.fragment.DialogWarning
import com.techkh.networkmonitor.util.CoroutineUtil
import kotlinx.android.synthetic.main.dialog_warning.*

class MonitorNetworkState(private val context: Context) {

    private var networkMonitor = NetworkMonitor(this.context)

    private fun run(title: String = "Internet Error", message: String = "No Internet Connection") {

        val dialog = DialogWarning(this.context)
        dialog.warningTitle.text = title
        dialog.warningMessage.text = message
        dialog.setCancelable(false)

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
}