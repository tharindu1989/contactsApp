package com.th.contact.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.th.contact.R
import kotlinx.android.synthetic.main.details_item_widget.view.*

/**
 * Created By Tharindu on 8/9/2019
 *
 */
class DetailItemWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.details_item_widget, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.DetailsItem, 0, 0
            )
            val labelStr = typedArray.getString(R.styleable.DetailsItem_details_label)
            labelTxt.text = labelStr

        }
    }

    /**
     * set Value
     */
    fun setValue(value: String?) {
        valueTxt.text = value
    }
}