package com.islandparadise14.mintable

import android.view.View

class ScheduleEntity(
    var originId: Int? = null,
    var scheduleName: String = "",
    var roomInfo: String = "",
    var scheduleDay: Int? = null,
    var startTime: String? = null,
    var endTime: String? = null,
    var backgroundColor: String = "#dddddd",
    var textColor: String = "#ffffff"
) {

    var scheduleClickListener: OnScheduleClickListener? = null
    var mOnClickListener: View.OnClickListener? = null

    fun setOnScheduleClickListener(listener: OnScheduleClickListener) {
        scheduleClickListener = listener
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        mOnClickListener = listener
    }

    interface OnScheduleClickListener {
        fun scheduleClicked(originId: Int)
    }

}
