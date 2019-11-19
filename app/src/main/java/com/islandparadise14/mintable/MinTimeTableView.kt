package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.mintable.view.*
import kotlin.math.roundToInt

class MinTimeTableView : LinearLayout, Utils {
    var data: ArrayList<ScheduleEntity>? = null
    var topMenuHeight: Int = 20
    var leftMenuWidth: Int = 30
    var cellHeight: Int = 50

    private var tableContext: Context? = null

    private var topMenuHeightPx: Float? = null
    private var leftMenuWidthPx: Float? = null
    private var cellHeightPx: Float? = null
    private var averageWidth: Int = 0

    private var tableStartTime: Int = 9
    private var tableEndTime: Int = 16

    private var dayList: Array<String>? = null

    private var radiusStyle: Int = 0

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

        array.recycle()
    }

    fun baseSetting(topMenuHeight: Int, leftMenuWidth: Int, cellHeight: Int) {
        this.topMenuHeight = topMenuHeight
        this.leftMenuWidth = leftMenuWidth
        this.cellHeight = cellHeight
    }

    fun initTable(dayList: Array<String>) {
        tableStartTime = 9
        tableEndTime = 16
        this.dayList = dayList

        topMenuHeightPx = dpToPx(tableContext!!, topMenuHeight.toFloat())
        leftMenuWidthPx = dpToPx(tableContext!!, leftMenuWidth.toFloat())
        cellHeightPx = dpToPx(tableContext!!, cellHeight.toFloat())

        leftMenu.layoutParams = LayoutParams(leftMenuWidthPx!!.roundToInt(), LayoutParams.WRAP_CONTENT)
        topMenu.layoutParams =  LayoutParams(LayoutParams.WRAP_CONTENT, topMenuHeightPx!!.roundToInt())
        mainTable.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        removeViews(arrayOf(zeroPoint, topMenu, timeCell, mainTable))

        zeroPoint.addView(ZeroPointView(tableContext!!, topMenuHeightPx!!.roundToInt(), leftMenuWidthPx!!.roundToInt()))


        averageWidth = (timetable.width - leftMenuWidthPx!!.roundToInt())/(this.dayList!!).size

        for(i in 0 until (this.dayList!!).size) {
            if(i == (this.dayList!!).size - 1) topMenu.addView(
                XxisEndView(
                    tableContext!!,
                    topMenuHeightPx!!.roundToInt(),
                    averageWidth,
                    (this.dayList!!)[(this.dayList!!).size - 1]
                )
            )
            else topMenu.addView(XxisView(tableContext!!, topMenuHeightPx!!.roundToInt(), averageWidth, dayList[i]))
        }

        recycleTimeCell()
    }

    fun addSchedules(schedules: ArrayList<ScheduleEntity>) {
        tableStartTime = 9
        tableEndTime = 16
        schedules.map {entity ->
            if(getHour(entity.startTime!!) < tableStartTime)
                tableStartTime = getHour(entity.startTime!!)
            if(getHour(entity.endTime!!) >= tableEndTime)
                tableEndTime = getHour(entity.endTime!!) + 1
        }

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
                        entity.scheduleClickListener!!,
                        tableStartTime,
                        radiusStyle
                    )
                )
        }
    }

    private fun recycleTimeCell () {
        for(i in 0 until (tableEndTime - tableStartTime)) {
            for(j in 0 until dayList!!.size) {
                mainTable.addView(TableCellView(tableContext!!, cellHeightPx!!.roundToInt(), averageWidth, (j * averageWidth), (i * cellHeightPx!!.roundToInt())))
            }
            if(i == (tableEndTime - tableStartTime)-1) timeCell.addView(YxisEndView(tableContext!!, cellHeightPx!!.roundToInt(), leftMenuWidthPx!!.roundToInt(), (tableStartTime + i).toString()))
            else timeCell.addView(YxisView(tableContext!!, cellHeightPx!!.roundToInt(), leftMenuWidthPx!!.roundToInt(), (tableStartTime + i).toString()))
        }
    }


}
