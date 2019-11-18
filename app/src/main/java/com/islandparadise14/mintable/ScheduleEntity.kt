package com.islandparadise14.mintable

class ScheduleEntity(
    var originId: Int? = null,
    var scheduleName: String = "",
    var roomInfo: String = "",
    var scheduleDay: ScheduleDay? = null,
    var startTime: String? = null,
    var endTime: String? = null,
    var backgroundColor: String = "#dddddd",
    var textColor: String = "#ffffff"
) {
    var onClick: () -> Unit = {}

    fun setOnClickListener(listener: () -> Unit) {
        onClick = listener
    }
}