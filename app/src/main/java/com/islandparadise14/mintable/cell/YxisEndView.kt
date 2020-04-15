package com.islandparadise14.mintable.cell

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.islandparadise14.mintable.R
import kotlinx.android.synthetic.main.y_xis_end.view.*

@SuppressLint("ViewConstructor")
class YxisEndView(context: Context, height: Int, width: Int, text: String, menuColor: Int, menuTextColor: Int, menuTextSize: Float) : LinearLayout(context) {
    init {
        initView(context, height, width, text, menuColor, menuTextColor, menuTextSize)
    }

    private fun initView(context: Context, height: Int, width: Int, text: String, menuColor: Int, menuTextColor: Int, menuTextSize: Float){
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.y_xis_end, this, true)

        leftMenuEndItem.layoutParams = LayoutParams(width, height)
        yXisEnd.text = text
        if(menuColor != 0)
            yXisEnd.setBackgroundColor(menuColor)
    }
}