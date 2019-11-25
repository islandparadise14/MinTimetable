package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.item_table_cell.view.*

@SuppressLint("ViewConstructor")
class TableCellView(context: Context, height: Int, width: Int, marginLeft: Int, marginTop: Int, cellColor: Int) : LinearLayout(context) {
    init {
        initView(context, height, width, marginLeft, marginTop, cellColor)
    }

    private fun initView(context: Context, height: Int, width: Int, marginLeft: Int, marginTop: Int, cellColor: Int){
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_table_cell, this, true)

        val layoutSetting = LayoutParams(width, height)
        layoutSetting.leftMargin = marginLeft
        layoutSetting.topMargin = marginTop
        cell.layoutParams = layoutSetting
        if(cellColor != 0)
            cellItem.setBackgroundColor(cellColor)
    }
}
