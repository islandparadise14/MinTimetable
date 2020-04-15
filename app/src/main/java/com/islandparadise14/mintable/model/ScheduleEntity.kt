package com.islandparadise14.mintable.model

import android.view.View

class ScheduleEntity(
    var originId: Int,
    var scheduleName: String,
    var roomInfo: String,
    var scheduleDay: Int,
    var startTime: String,
    var endTime: String,
    var backgroundColor: String = "#dddddd",
    var textColor: String = "#ffffff"
) {

    var mOnClickListener: View.OnClickListener? = null

    fun setOnClickListener(listener: View.OnClickListener) {
        mOnClickListener = listener
    }

}
