package com.islandparadise14.mintable.tableinterface

import com.islandparadise14.mintable.model.ScheduleEntity

interface OnScheduleLongClickListener {
    fun scheduleLongClicked(entity: ScheduleEntity)
}