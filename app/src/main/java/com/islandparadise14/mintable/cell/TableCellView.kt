package com.islandparadise14.mintable.cell

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.islandparadise14.mintable.R
import com.islandparadise14.mintable.tableinterface.OnTimeCellClickListener
import kotlinx.android.synthetic.main.item_table_cell.view.*

@SuppressLint("ViewConstructor")
class TableCellView(context: Context,
                    height: Int,
                    width: Int,
                    marginLeft: Int,
                    marginTop: Int,
                    cellColor: Int,
                    timeCellClickListener: OnTimeCellClickListener?,
                    scheduleDay: Int,
                    time: Int
) : LinearLayout(context) {
    init {
        initView(context, height, width, marginLeft, marginTop, cellColor, timeCellClickListener, scheduleDay, time)
    }

    private fun initView(context: Context,
                         height: Int,
                         width: Int,
                         marginLeft: Int,
                         marginTop: Int,
                         cellColor: Int,
                         timeCellClickListener: OnTimeCellClickListener?,
                         scheduleDay: Int,
                         time: Int
    ){
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_table_cell, this, true)

        cell.setOnClickListener {
            timeCellClickListener?.timeCellClicked(scheduleDay, time)
        }

        val layoutSetting = LayoutParams(width, height)
        layoutSetting.leftMargin = marginLeft
        layoutSetting.topMargin = marginTop
        cell.layoutParams = layoutSetting
        if(cellColor != 0)
            cellItem.setBackgroundColor(cellColor)
    }
}
