package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.mintable.view.*
import kotlin.math.roundToInt

class MinTimeTableView : LinearLayout, Utils {
    private var topMenuHeight: Int = 20
    private var leftMenuWidth: Int = 30
    private var cellHeight: Int = 50

    private var isRatio: Boolean = false
    private var cellRatio: Float = 0f

    private var tableContext: Context? = null

    private var topMenuHeightPx: Float? = null
    private var leftMenuWidthPx: Float? = null
    private var cellHeightPx: Float? = null
    private var averageWidth: Int = 0
    private var widthPaddingPx: Float? = null

    private var tableStartTime: Int = 9
    private var tableEndTime: Int = 16

    private var dayList: Array<String>? = null

    private var radiusStyle: Int = 0
    private var twentyFourHourClock = true
    private var cellColor = 0
    private var menuColor = 0
    private var lineColor = 0

    private var isFullScreen = false
    private var widthPadding = 0

    constructor(context: Context) : super(context){
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context, attrs)
    }

    @SuppressLint("Recycle")
    fun initView(context: Context, attrs: AttributeSet?) {
        tableContext = context
        val inflater = LayoutInflater.from(tableContext)
        val v = inflater.inflate(R.layout.mintable, this, false)
        addView(v)


        if (attrs == null) {
            return
        }

        val array = tableContext!!.obtainStyledAttributes(attrs, R.styleable.MinTimeTableView)

        radiusStyle = array.getInt(R.styleable.MinTimeTableView_radius_option, 0)
        twentyFourHourClock = array.getBoolean(R.styleable.MinTimeTableView_setTwentyFourHourClock, true)
        cellColor = array.getColor(R.styleable.MinTimeTableView_cellColor, 0)
        menuColor = array.getColor(R.styleable.MinTimeTableView_menuColor, 0)
        lineColor = array.getColor(R.styleable.MinTimeTableView_lineColor, 0)

        if(lineColor != 0) {
            mainTable.setBackgroundColor(lineColor)
            topMenu.setBackgroundColor(lineColor)
            leftMenu.setBackgroundColor(lineColor)
        }

        isFullScreen = array.getBoolean(R.styleable.MinTimeTableView_isFullWidth, false)
        widthPadding = array.getInteger(R.styleable.MinTimeTableView_widthPadding, 0)

        array.recycle()
    }

    fun baseSetting(topMenuHeight: Int, leftMenuWidth: Int, cellHeight: Int) {
        this.topMenuHeight = topMenuHeight
        this.leftMenuWidth = leftMenuWidth
        this.cellHeight = cellHeight
        isRatio = false
    }

    fun ratioCellSetting(topMenuHeight: Int, leftMenuWidth: Int, cellRatio: Float) {
        this.topMenuHeight = topMenuHeight
        this.leftMenuWidth = leftMenuWidth
        this.cellRatio = cellRatio
        isRatio = true
    }

    fun initTable(dayList: Array<String>) {
        tableStartTime = 9
        tableEndTime = 16
        this.dayList = dayList

        topMenuHeightPx = dpToPx(tableContext!!, topMenuHeight.toFloat())
        leftMenuWidthPx = dpToPx(tableContext!!, leftMenuWidth.toFloat())
        widthPaddingPx = dpToPx(tableContext!!, widthPadding.toFloat())

        averageWidth = if (isFullScreen)
            (getWindowWidth(tableContext!!) - (widthPaddingPx!!.roundToInt() * 2) - leftMenuWidthPx!!.roundToInt()) / (this.dayList!!).size
        else
            (timetable.width - leftMenuWidthPx!!.roundToInt()) / (this.dayList!!).size

        if (isFullScreen) {
            timetable.setPadding(widthPaddingPx!!.roundToInt(), 0, widthPaddingPx!!.roundToInt(), 0)
        }

        cellHeightPx = if (isRatio) averageWidth * cellRatio
        else dpToPx(tableContext!!, cellHeight.toFloat())

        leftMenu.layoutParams = LayoutParams(leftMenuWidthPx!!.roundToInt(), LayoutParams.WRAP_CONTENT)
        topMenu.layoutParams =  LayoutParams(LayoutParams.WRAP_CONTENT, topMenuHeightPx!!.roundToInt())
        mainTable.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        removeViews(arrayOf(zeroPoint, topMenu, timeCell, mainTable))

        zeroPoint.addView(ZeroPointView(tableContext!!, topMenuHeightPx!!.roundToInt(), leftMenuWidthPx!!.roundToInt(), menuColor))

        for(i in 0 until (this.dayList!!).size) {
            if(i == (this.dayList!!).size - 1) topMenu.addView(
                XxisEndView(
                    tableContext!!,
                    topMenuHeightPx!!.roundToInt(),
                    averageWidth,
                    (this.dayList!!)[(this.dayList!!).size - 1],
                    menuColor
                )
            )
            else topMenu.addView(XxisView(tableContext!!, topMenuHeightPx!!.roundToInt(), averageWidth, dayList[i], menuColor))
        }

        recycleTimeCell()
    }

    fun updateSchedules(schedules: ArrayList<ScheduleEntity>) {
        if (schedules.size == 0) {
            return
        }
        calculateTime(schedules)

        removeViews(arrayOf(timeCell, mainTable))
        recycleTimeCell()

        schedules.map {entity ->
            if(entity.originId != null && entity.scheduleDay != null && entity.startTime != null && entity.endTime != null)
                mainTable.addView(
                    ScheduleView(
                        tableContext!!,
                        entity.originId!!,
                        entity.scheduleName,
                        entity.roomInfo,
                        entity.scheduleDay!!,
                        getTotalMinute(entity.startTime!!),
                        getTotalMinute(entity.endTime!!),
                        entity.backgroundColor,
                        entity.textColor,
                        cellHeightPx!!.roundToInt(),
                        averageWidth,
                        entity.scheduleClickListener,
                        entity.mOnClickListener,
                        tableStartTime,
                        radiusStyle
                    )
                )
        }
    }

    private fun calculateTime (schedules: ArrayList<ScheduleEntity>) {
        tableStartTime = getHour(schedules[0].startTime!!)
        tableEndTime = 16
        schedules.map {entity ->
            if(getHour(entity.startTime!!) < tableStartTime)
                tableStartTime = getHour(entity.startTime!!)
            if(getHour(entity.endTime!!) >= tableEndTime)
                tableEndTime = getHour(entity.endTime!!) + 1
        }
        if ((tableEndTime - tableStartTime) < 7) {
            tableEndTime = tableStartTime + 7
        }
    }

    private fun recycleTimeCell () {
        for(i in 0 until (tableEndTime - tableStartTime)) {
            for(j in 0 until dayList!!.size) {
                mainTable.addView(TableCellView(tableContext!!, cellHeightPx!!.roundToInt(), averageWidth, (j * averageWidth), (i * cellHeightPx!!.roundToInt()), cellColor))
            }
            val hour = if (twentyFourHourClock) (tableStartTime + i)
            else {
                if ((tableStartTime + i)!=12) (tableStartTime + i) % 12
                else (tableStartTime + i)
            }
            if(i == (tableEndTime - tableStartTime)-1) timeCell.addView(YxisEndView(tableContext!!, cellHeightPx!!.roundToInt(), leftMenuWidthPx!!.roundToInt(), hour.toString(), menuColor))
            else timeCell.addView(YxisView(tableContext!!, cellHeightPx!!.roundToInt(), leftMenuWidthPx!!.roundToInt(), hour.toString(), menuColor))
        }
    }
}
