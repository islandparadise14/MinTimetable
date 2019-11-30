package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.item_schedule.view.*

@SuppressLint("ViewConstructor")
class ScheduleView(context: Context,
                   entity: ScheduleEntity,
                   height: Int,
                   width: Int,
                   scheduleClickListener: OnScheduleClickListener?,
                   tableStartTime: Int,
                   radiusStyle: Int
) : BaseTimeTable(context) {
    init {
        setting(
            context,
            entity,
            height,
            width,
            scheduleClickListener,
            tableStartTime,
            radiusStyle
        )
    }

    @SuppressLint("RtlHardcoded")
    private fun setting(context: Context,
                        entity: ScheduleEntity,
                        height: Int,
                        width: Int,
                        scheduleClickListener: OnScheduleClickListener?,
                        tableStartTime: Int,
                        radiusStyle: Int
    ) {

        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_schedule, this, true)

        val duration = getTotalMinute(entity.endTime) - getTotalMinute(entity.startTime)

        val layoutSetting = LayoutParams(width, ((height * duration).toDouble() / 60).toInt())
        layoutSetting.topMargin = (((height * getTotalMinute(entity.startTime)).toDouble() / 60) - (height * tableStartTime)).toInt()
        layoutSetting.leftMargin = width * entity.scheduleDay

        tableItem.layoutParams = layoutSetting

        tableItem.setOnClickListener {
            scheduleClickListener?.scheduleClicked(entity)
            entity.mOnClickListener?.onClick(tableItem)
        }


        val layoutText = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        val cornerRadius = dpToPx(context, RADIUS.toFloat())
        val roundRadius = dpToPx(context, ROUND.toFloat())

        val border = GradientDrawable()
        border.setColor(Color.parseColor(entity.backgroundColor))
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

        name.text = entity.scheduleName
        room.text = entity.roomInfo

        name.setTextColor(Color.parseColor(entity.textColor))
        room.setTextColor(Color.parseColor(entity.textColor))
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
