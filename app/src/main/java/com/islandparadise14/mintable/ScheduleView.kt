package com.islandparadise14.mintable

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.item_schedule.view.*

class ScheduleView : LinearLayout {

    constructor(context: Context) : super(context) {
        setting(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setting(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setting(context)
    }

    fun setting(context: Context) {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_schedule, this, true)

        name.text = "333333333"

//        val border = GradientDrawable()
//        border.setColor(0xFFFFFF)
//        border.setStroke(1, -0x1000000)
//        border.cornerRadius = 0.5f
//        border.cornerRadii = floatArrayOf(0.5f, 0.3f, 0.1f, 0.8f)
//        linearLayout!!.background = border
//
//
//        var dd = MinTimeTableView(context)
//        dd.data
    }
}
