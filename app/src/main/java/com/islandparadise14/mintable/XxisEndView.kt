package com.islandparadise14.mintable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.x_xis_end.view.*

class XxisEndView : LinearLayout {
    constructor(context: Context, topMenuHeight: Int) : super(context) {
        initView(context, topMenuHeight, null)
    }

    constructor(context: Context, topMenuHeight: Int, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, topMenuHeight, attrs)
    }

    constructor(context: Context, topMenuHeight: Int, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context, topMenuHeight, attrs)
    }

    fun initView(context: Context, topMenuHeight: Int, attrs: AttributeSet?){
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.x_xis_end, this, true)

        topMenuEndItem.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, topMenuHeight)
    }
}