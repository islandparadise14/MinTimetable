package com.islandparadise14.mintable

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.zero_point.view.*

class ZeroPointView(context: Context, height: Int, width: Int) : LinearLayout(context) {
    init {
        initView(context, height, width)
    }

    private fun initView(context: Context, height: Int, width: Int) {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.zero_point, this, true)

        zeroLayout.layoutParams = LayoutParams(width, height)
    }
}