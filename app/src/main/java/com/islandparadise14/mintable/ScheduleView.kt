package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.item_schedule.view.*

@SuppressLint("ViewConstructor")
class ScheduleView(context: Context,
                   originId: Int,
                   scheduleName: String,
                   roomInfo: String,
                   scheduleDay: ScheduleDay,
                   startTime: String,
                   endTime: String,
                   backgroundColor: String,
                   textColor: String,
                   height: Int,
                   width: Int,
                   onClick: () -> Unit
) : LinearLayout(context) {

    init {
        setting(
            context,
            originId,
            scheduleName,
            roomInfo,
            scheduleDay,
            startTime,
            endTime,
            backgroundColor,
            textColor,
            height,
            width,
            onClick
        )
    }

    private fun setting(context: Context,
                        originId: Int,
                        scheduleName: String,
                        roomInfo: String,
                        scheduleDay: ScheduleDay,
                        startTime: String,
                        endTime: String,
                        backgroundColor: String,
                        textColor: String,
                        height: Int,
                        width: Int,
                        onClick: () -> Unit
    ) {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_schedule, this, true)

        val startResult = startTime.split(":")
        val endResult = endTime.split(":")
        val startMinute = startResult[0].toInt() * 60 + startResult[1].toInt()
        val endMinute = endResult[0].toInt() * 60 + endResult[1].toInt()

        val duration = endMinute - startMinute

        val layoutSetting = LayoutParams(width, ((height * duration).toDouble() / 60).toInt())
        layoutSetting.topMargin = (((height * startMinute).toDouble() / 60) - (height * 9)).toInt()
        layoutSetting.leftMargin = width * scheduleDay.day

        tableItem.layoutParams = layoutSetting

        tableItem.setOnClickListener {
            Log.d("tableItemClick", originId.toString())
            onClick()
        }
        name.text = scheduleName
        room.text = roomInfo

        tableItem.setBackgroundColor(Color.parseColor(backgroundColor))
        name.setTextColor(Color.parseColor(textColor))
        room.setTextColor(Color.parseColor(textColor))

        //    .setBackgroundColor(Color.parseColor("#333333"))

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
