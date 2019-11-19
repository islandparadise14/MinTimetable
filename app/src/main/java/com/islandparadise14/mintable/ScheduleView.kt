package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
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
                   tableStartTime: Int,
                   radiusStyle: Int
) : LinearLayout(context), Utils {
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
            tableStartTime,
            radiusStyle
        )
    }

    @SuppressLint("RtlHardcoded")
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
                        tableStartTime: Int,
                        radiusStyle: Int
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


        val layoutText = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        val cornerRadius = dpToPx(context, Companion.RADIUS.toFloat())
        val roundRadius = dpToPx(context, Companion.ROUND.toFloat())

        val border = GradientDrawable()
        border.setColor(Color.parseColor(backgroundColor))
        border.shape = GradientDrawable.RECTANGLE

        when (radiusStyle) {
            NONE -> {}
            LEFT -> {
                layoutText.leftMargin = (width.toDouble() * 0.15).toInt()
                tableItem.gravity = Gravity.RIGHT
                name.layoutParams = layoutText
                name.gravity = Gravity.RIGHT
                room.gravity = Gravity.RIGHT

                border.cornerRadii = floatArrayOf(cornerRadius, cornerRadius, 0f, 0f, cornerRadius, cornerRadius, 0f, 0f)
            }
            RIGHT -> {
                layoutText.rightMargin = (width.toDouble() * 0.15).toInt()
                name.layoutParams = layoutText

                border.cornerRadii = floatArrayOf(0f, 0f, cornerRadius, cornerRadius, 0f, 0f, cornerRadius, cornerRadius)
            }
            ALL -> {
                border.cornerRadius = roundRadius
            }
        }

        tableItem.background = border

        name.text = scheduleName
        room.text = roomInfo

        name.setTextColor(Color.parseColor(textColor))
        room.setTextColor(Color.parseColor(textColor))
    }

    companion object {
        const val NONE = 0
        const val LEFT = 1
        const val RIGHT = 2
        const val ALL = 3
        private const val RADIUS = 30
        private const val ROUND = 15
    }
}
