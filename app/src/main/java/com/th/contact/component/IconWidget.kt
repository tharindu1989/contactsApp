package com.th.contact.component

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.th.contact.R
import kotlinx.android.synthetic.main.icon_widget.view.*

/**
 * Created By Tharindu on 8/9/2019
 *
 */
class IconWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.icon_widget, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.IconWidget, 0, 0
            )

            val imageRes = typedArray.getResourceId(R.styleable.IconWidget_iconSrc, R.drawable.ic_call_black_24dp)
            val titleStr = typedArray.getString(R.styleable.IconWidget_title)
            iconImg.setImageResource(imageRes)
            titleTxt.text = titleStr

        }
    }
}