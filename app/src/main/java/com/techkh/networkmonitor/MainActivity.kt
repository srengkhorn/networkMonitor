package com.techkh.networkmonitor

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         networkMonitor = NetworkMonitor(this, object: NetworkMonitor.WarningListener {
             override fun onCancel() {
                 Toast.makeText(applicationContext, "Cancel click", Toast.LENGTH_SHORT).show()
             }

             override fun onTryAgain() {
                 Toast.makeText(applicationContext, "Try Again click", Toast.LENGTH_SHORT).show()
             }
         })
        networkMonitor.start()
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