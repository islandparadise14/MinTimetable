package com.islandparadise14.mintable.cell

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.islandparadise14.mintable.R
import kotlinx.android.synthetic.main.zero_point.view.*

@SuppressLint("ViewConstructor")
class ZeroPointView(context: Context, height: Int, width: Int, menuColor: Int) : LinearLayout(context) {
    init {
        initView(context, height, width, menuColor)
    }

    private fun initView(context: Context, height: Int, width: Int, menuColor: Int) {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.zero_point, this, true)

        zeroLayout.layoutParams = LayoutParams(width, height)
        if(menuColor != 0)
            zeroItem.setBackgroundColor(menuColor)
    }
}