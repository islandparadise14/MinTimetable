package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.x_xis_end.view.*

@SuppressLint("ViewConstructor")
class XxisEndView(context: Context, height: Int, width: Int, text: String, menuColor: Int) : LinearLayout(context) {
    init {
        initView(context, height, width, text, menuColor)
    }

    fun initView(context: Context, height: Int, width: Int, text: String, menuColor: Int) {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.x_xis_end, this, true)

        topMenuEndItem.layoutParams = LayoutParams(width, height)
        xXisEnd.text = text
        if(menuColor != 0)
            xXisEnd.setBackgroundColor(menuColor)
    }
}