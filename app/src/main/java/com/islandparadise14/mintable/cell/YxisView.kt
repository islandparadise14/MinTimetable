package com.islandparadise14.mintable.cell

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.islandparadise14.mintable.R
import kotlinx.android.synthetic.main.y_xis.view.*

@SuppressLint("ViewConstructor")
class YxisView(context: Context, height: Int, width: Int, text: String, menuColor: Int) : LinearLayout(context) {
    init {
        initView(context, height, width, text, menuColor)
    }

    private fun initView(context: Context, height: Int, width: Int, text: String, menuColor: Int){
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.y_xis, this, true)

        leftMenuItem.layoutParams = LayoutParams(width, height)
        yXis.text = text
        if(menuColor != 0)
            yXis.setBackgroundColor(menuColor)
    }
}