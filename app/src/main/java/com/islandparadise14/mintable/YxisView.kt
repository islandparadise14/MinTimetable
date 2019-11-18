package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.mintable.view.*
import kotlinx.android.synthetic.main.x_xis.view.*
import kotlinx.android.synthetic.main.y_xis.view.*

@SuppressLint("ViewConstructor")
class YxisView(context: Context, height: Int, width: Int, text: String) : LinearLayout(context) {
    init {
        initView(context, height, width, text)
    }

    private fun initView(context: Context, height: Int, width: Int, text: String){
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.y_xis, this, true)

        leftMenuItem.layoutParams = LayoutParams(width, height)
        yXis.text = text
    }
}