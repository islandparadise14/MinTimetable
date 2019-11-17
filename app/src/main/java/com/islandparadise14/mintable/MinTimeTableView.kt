package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.mintable.view.*

class MinTimeTableView : LinearLayout {
    var data: ArrayList<ScheduleEntity>? = null
    var topMenuHeight: Int = 100
    var leftMenuWidth: Int = 90
    var cellHeight: Int = 200

    constructor(context: Context) : super(context){
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context, attrs)
    }


    @SuppressLint("Recycle")
    fun initView(context: Context, attrs: AttributeSet?) {
        val inflater = LayoutInflater.from(context)
        var v = inflater.inflate(R.layout.mintable, this, false)
        addView(v)
    }

    fun update(context: Context) {
        leftMenu.layoutParams = LayoutParams(leftMenuWidth, LayoutParams.WRAP_CONTENT)
        topMenu.layoutParams =  LayoutParams(LayoutParams.MATCH_PARENT, topMenuHeight)

        removeViews()

        zeroPoint.addView(ZeroPointView(context, topMenuHeight, leftMenuWidth))


        var list = ArrayList<String>()
        list.add("월")
        list.add("화")
        list.add("수")
        list.add("목")
        list.add("금")

        var averageWidth = (timetable.width - leftMenuWidth)/list.size

        for(i in 0..3) {
            topMenu.addView(XxisView(context, topMenuHeight, averageWidth, list[i]))
        }
        topMenu.addView(XxisEndView(context, topMenuHeight, averageWidth, list[4]))

        for(i in 0..7) {
            timeCell.addView(YxisView(context, cellHeight, leftMenuWidth, (9 + i).toString()))
        }
        timeCell.addView(YxisEndView(context, cellHeight, leftMenuWidth, "17"))

        for(i in 0..8) {
            for(j in 0..4) {
                mainTable.addView(TableCellView(context, cellHeight, averageWidth, (j * averageWidth), (i * cellHeight)))
            }
        }
    }

    fun removeViews(){
        zeroPoint.removeAllViews()
        topMenu.removeAllViews()
        timeCell.removeAllViews()
        mainTable.removeAllViews()
    }
}
