package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.y_xis.view.*
import kotlinx.android.synthetic.main.y_xis_end.view.*

@SuppressLint("ViewConstructor")
class YxisEndView(context: Context, height: Int, width: Int, text: String) : LinearLayout(context) {
    init {
        initView(context, height, width, text, null)
    }

    fun initView(context: Context, height: Int, width: Int, text: String, attrs: AttributeSet?){
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.y_xis_end, this, true)

        leftMenuEndItem.layoutParams = LayoutParams(width, height)
        yXisEnd.text = text
    }
}