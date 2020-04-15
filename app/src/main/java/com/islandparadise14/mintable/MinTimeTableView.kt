package com.islandparadise14.mintable

import android.content.Context
import android.util.AttributeSet
import com.islandparadise14.mintable.cell.XxisEndView
import com.islandparadise14.mintable.cell.XxisView
import com.islandparadise14.mintable.cell.ZeroPointView
import com.islandparadise14.mintable.model.ScheduleEntity
import com.islandparadise14.mintable.schedule.ScheduleView
import com.islandparadise14.mintable.tableinterface.OnScheduleClickListener
import com.islandparadise14.mintable.tableinterface.OnScheduleLongClickListener
import com.islandparadise14.mintable.tableinterface.OnTimeCellClickListener
import com.islandparadise14.mintable.utils.dpToPx
import com.islandparadise14.mintable.utils.getWindowWidth
import kotlinx.android.synthetic.main.mintable.view.*
import kotlin.math.roundToInt

class MinTimeTableView : BaseTimeTable {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    //table setting function

    fun baseSetting(topMenuHeight: Int, leftMenuWidth: Int, cellHeight: Int) {
        super.topMenuHeight = topMenuHeight
        super.leftMenuWidth = leftMenuWidth
        super.cellHeight = cellHeight
        super.isRatio = false
    }

    fun ratioCellSetting(topMenuHeight: Int, leftMenuWidth: Int, cellRatio: Float) {
        super.topMenuHeight = topMenuHeight
        super.leftMenuWidth = leftMenuWidth
        super.cellRatio = cellRatio
        super.isRatio = true
    }

    fun initTable(dayList: Array<String>) {
        super.tableStartTime = 9
        super.tableEndTime = 16
        super.dayList = dayList

        super.topMenuHeightPx = dpToPx(
            super.tableContext,
            super.topMenuHeight.toFloat()
        )
        super.leftMenuWidthPx = dpToPx(
            super.tableContext,
            super.leftMenuWidth.toFloat()
        )
        super.widthPaddingPx = dpToPx(
            super.tableContext,
            super.widthPadding.toFloat()
        )

        super.averageWidth = if (super.isFullScreen)
            (getWindowWidth(super.tableContext) - (super.widthPaddingPx.roundToInt() * 2) - super.leftMenuWidthPx.roundToInt()) / (super.dayList).size
        else
            (timetable.width - super.leftMenuWidthPx.roundToInt()) / (super.dayList).size

        if (super.isFullScreen) {
            timetable.setPadding(super.widthPaddingPx.roundToInt(), 0, super.widthPaddingPx.roundToInt(), 0)
        }

        super.cellHeightPx = if (super.isRatio) super.averageWidth * super.cellRatio
        else dpToPx(
            super.tableContext,
            super.cellHeight.toFloat()
        )

        leftMenu.layoutParams = LayoutParams(super.leftMenuWidthPx.roundToInt(), LayoutParams.WRAP_CONTENT)
        topMenu.layoutParams =  LayoutParams(LayoutParams.WRAP_CONTENT, super.topMenuHeightPx.roundToInt())
        mainTable.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        if (super.border) {
            borderBox.setBackgroundColor(super.lineColor)
            borderBox.setPadding(
                dpToPx(
                    super.tableContext,
                    1f
                ).roundToInt(),
                dpToPx(
                    super.tableContext,
                    1f
                ).roundToInt(),0,0)
            averageWidth -= 1
        }
        com.islandparadise14.mintable.utils.removeViews(
            arrayOf(
                zeroPoint,
                topMenu,
                timeCell,
                mainTable
            )
        )

        zeroPoint.addView(
            ZeroPointView(
                super.tableContext,
                super.topMenuHeightPx.roundToInt(),
                super.leftMenuWidthPx.roundToInt(),
                super.menuColor
            )
        )

        for(i in (super.dayList).indices) {
            if (super.xEndLine) topMenu.addView(
                XxisView(
                    super.tableContext,
                    super.topMenuHeightPx.roundToInt(),
                    super.averageWidth,
                    dayList[i],
                    super.menuColor,
                    super.menuTextColor,
                    super.menuTextSize
                )
                )
            else {
                if (i == (super.dayList).size - 1) topMenu . addView (
                    XxisEndView(
                        super.tableContext,
                        super.topMenuHeightPx.roundToInt(),
                        super.averageWidth,
                        (super.dayList)[(super.dayList).size - 1],
                        super.menuColor,
                        super.menuTextColor,
                        dpToPx(tableContext, super.menuTextSize)
                    )
                        )
                else topMenu.addView(
                    XxisView(
                        super.tableContext,
                        super.topMenuHeightPx.roundToInt(),
                        super.averageWidth,
                        dayList[i],
                        super.menuColor,
                        super.menuTextColor,
                        dpToPx(tableContext, super.menuTextSize)
                    )
                )
            }
        }

        super.recycleTimeCell()
    }

    fun updateSchedules(schedules: ArrayList<ScheduleEntity>) {
        super.schedules = schedules
        if (super.schedules.size == 0) {
            return
        }
        super.calculateTime(super.schedules)

        com.islandparadise14.mintable.utils.removeViews(
            arrayOf(
                timeCell,
                mainTable
            )
        )
        super.recycleTimeCell()

        super.schedules.map {entity ->
                mainTable.addView(
                    ScheduleView(
                        super.tableContext,
                        entity,
                        super.cellHeightPx.roundToInt(),
                        super.averageWidth,
                        super.scheduleClickListener,
                        super.scheduleLongClickListener,
                        super.tableStartTime,
                        super.radiusStyle
                    )
                )
        }
    }


    fun setOnScheduleClickListener(listener: OnScheduleClickListener) {
        super.scheduleClickListener = listener
    }

    fun setOnScheduleLongClickListener(listener: OnScheduleLongClickListener) {
        super.scheduleLongClickListener = listener
    }

    fun setOnTimeCellClickListener(listener: OnTimeCellClickListener) {
        super.timeCellClickListener = listener
    }


    //attrs setting function

    fun radiusOption(option: Int) {
        super.radiusStyle = option
        updateSchedules(super.schedules)
    }

    fun cellColor(color: Int) {
        super.cellColor = color
        initTable(super.dayList)
        updateSchedules(super.schedules)
    }

    fun menuColor(color: Int) {
        super.menuColor = color
        initTable(super.dayList)
        updateSchedules(super.schedules)
    }

    fun lineColor(color: Int) {
        super.lineColor = color
        initTable(super.dayList)
        updateSchedules(super.schedules)
    }

    fun isTwentyFourHourClock(boolean: Boolean) {
        super.isTwentyFourHourClock = boolean
        initTable(super.dayList)
        updateSchedules(super.schedules)
    }

    fun isFullWidth(boolean: Boolean) {
        super.isFullScreen = boolean
        initTable(super.dayList)
        updateSchedules(super.schedules)
    }

    fun widthPadding(dp: Int) {
        super.widthPadding = dp
        initTable(super.dayList)
        updateSchedules(super.schedules)
    }

    fun xEndLine(boolean: Boolean) {
        super.xEndLine = boolean
        initTable(super.dayList)
        updateSchedules(super.schedules)
    }

    fun yEndLine(boolean: Boolean) {
        super.yEndLine = boolean
        initTable(super.dayList)
        updateSchedules(super.schedules)
    }

    fun border(boolean: Boolean) {
        super.border = boolean
        initTable(super.dayList)
        updateSchedules(super.schedules)
    }
}
