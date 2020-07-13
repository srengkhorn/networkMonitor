package com.techkh.networkmonitor.fragment

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.techkh.networkmonitor.R
import kotlinx.android.synthetic.main.dialog_warning.*

class DialogWarning(context: Context) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_warning)
    }
}