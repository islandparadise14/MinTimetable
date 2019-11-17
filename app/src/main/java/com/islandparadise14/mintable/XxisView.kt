package com.islandparadise14.mintable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.x_xis.view.*

class XxisView : LinearLayout{
    constructor(context: Context, topMenuHeight: Int, text: String) : super(context) {
        initView(context, topMenuHeight, text, null)
    }

    constructor(context: Context, topMenuHeight: Int, text: String, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, topMenuHeight, text, attrs)
    }

    constructor(context: Context, topMenuHeight: Int, text: String, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context, topMenuHeight, text, attrs)
    }

    fun initView(context: Context, topMenuHeight: Int, text: String, attrs: AttributeSet?){
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.x_xis, this, true)

        topMenuItem.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, topMenuHeight)
        xXis.text = text
    }
}