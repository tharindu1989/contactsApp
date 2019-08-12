package com.th.contact.component

import android.app.Dialog
import android.content.Context
import com.th.contact.R

/**
 * Created By Tharindu on 8/12/2019
 *
 */
class ProgressDialog : Dialog {

    constructor(context: Context) : super(context, android.R.style.Theme_Translucent_NoTitleBar) {

        this.setContentView(R.layout.dialog_progress_layout)
        this.setCancelable(true)
    }

    fun showDialog() {
        if (!this.isShowing) {
            show()
        }
    }

    fun hideDialog() {
        dismiss()
    }
}