package com.islandparadise14.mintable.cell

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.islandparadise14.mintable.R
import kotlinx.android.synthetic.main.x_xis.view.*

@SuppressLint("ViewConstructor")
class XxisView(context: Context, height: Int, width: Int, text: String, menuColor: Int, menuTextColor: Int, menuTextSize: Float) : LinearLayout(context) {
    init {
        initView(context, height, width, text, menuColor, menuTextColor, menuTextSize)
    }

    private fun initView(context: Context, height: Int, width: Int, text: String, menuColor: Int, menuTextColor: Int, menuTextSize: Float){
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.x_xis, this, true)

        topMenuItem.layoutParams = LayoutParams(width, height)
        xXis.text = text
        if(menuColor != 0)
            xXis.setBackgroundColor(menuColor)
    }
}