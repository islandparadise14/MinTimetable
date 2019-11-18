package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.marginLeft
import kotlinx.android.synthetic.main.item_table_cell.view.*

@SuppressLint("ViewConstructor")
class TableCellView(context: Context, height: Int, width: Int, marginLeft: Int, marginTop: Int) : LinearLayout(context) {
    init {
        initView(context, height, width, marginLeft, marginTop)
    }

    private fun initView(context: Context, height: Int, width: Int, marginLeft: Int, marginTop: Int){
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_table_cell, this, true)

        var layoutSetting = LayoutParams(width, height)
        layoutSetting.leftMargin = marginLeft
        layoutSetting.topMargin = marginTop
        cell.layoutParams = layoutSetting
    }
}
