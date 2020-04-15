package com.islandparadise14.mintable.tableinterface

import com.islandparadise14.mintable.model.ScheduleEntity

interface OnScheduleClickListener {
    fun scheduleClicked(entity: ScheduleEntity)
}