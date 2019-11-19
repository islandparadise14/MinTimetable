package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.item_schedule.view.*

@SuppressLint("ViewConstructor")
class ScheduleView(context: Context,
                   originId: Int,
                   scheduleName: String,
                   roomInfo: String,
                   scheduleDay: ScheduleDay,
                   startMinute: Int,
                   endMinute: Int,
                   backgroundColor: String,
                   textColor: String,
                   height: Int,
                   width: Int,
                   scheduleClickListener: ScheduleEntity.OnScheduleClickListener?,
                   tableStartTime: Int
) : LinearLayout(context) {

    init {
        setting(
            context,
            originId,
            scheduleName,
            roomInfo,
            scheduleDay,
            startMinute,
            endMinute,
            backgroundColor,
            textColor,
            height,
            width,
            scheduleClickListener,
            tableStartTime
        )
    }

    private fun setting(context: Context,
                        originId: Int,
                        scheduleName: String,
                        roomInfo: String,
                        scheduleDay: ScheduleDay,
                        startMinute: Int,
                        endMinute: Int,
                        backgroundColor: String,
                        textColor: String,
                        height: Int,
                        width: Int,
                        scheduleClickListener: ScheduleEntity.OnScheduleClickListener?,
                        tableStartTime: Int
    ) {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_schedule, this, true)

        val duration = endMinute - startMinute

        val layoutSetting = LayoutParams(width, ((height * duration).toDouble() / 60).toInt())
        layoutSetting.topMargin = (((height * startMinute).toDouble() / 60) - (height * tableStartTime)).toInt()
        layoutSetting.leftMargin = width * scheduleDay.day

        tableItem.layoutParams = layoutSetting

        tableItem.setOnClickListener {
            scheduleClickListener?.scheduleClicked(originId)
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
