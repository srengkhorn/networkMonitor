package com.techkh.networkmonitor

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NetworkMonitor.ConnectionListener {

    lateinit var networkMonitor: NetworkMonitor
    private lateinit var listener: NetworkMonitor.ConnectionListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         this.listener = this
         networkMonitor = NetworkMonitor(this, listener)
         networkMonitor.start()
    }

    override fun isConnected() {
        networkMonitor.hideAlert()
        statusNetwork.text = "Internet Connected"
    }

    override fun isNotConnected() {
        statusNetwork.text = "Internet is NOT Connected"
        networkMonitor.showAlert()
        networkMonitor.onDialogActionListener(object: NetworkMonitor.DialogListener{
            override fun onCancel() {
                Toast.makeText(applicationContext, "Clicked Cancel", Toast.LENGTH_SHORT).show()
            }

            override fun onTryAgain() {
                Toast.makeText(applicationContext, "Clicked Try", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }
}