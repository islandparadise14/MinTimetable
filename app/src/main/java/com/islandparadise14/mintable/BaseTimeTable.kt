package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.mintable.view.*
import kotlin.math.roundToInt

open class BaseTimeTable : LinearLayout {

    protected var topMenuHeight: Int = 20
    protected var leftMenuWidth: Int = 30
    protected var cellHeight: Int = 50

    protected var isRatio: Boolean = false
    protected var cellRatio: Float = 0f

    protected var tableContext: Context = context

    protected var topMenuHeightPx: Float = 0.0f
    protected var leftMenuWidthPx: Float = 0.0f
    protected var cellHeightPx: Float = 0.0f
    protected var averageWidth: Int = 0
    protected var widthPaddingPx: Float = 0.0f

    protected var tableStartTime: Int = 9
    protected var tableEndTime: Int = 16

    protected var dayList: Array<String> = arrayOf()

    protected var radiusStyle: Int = 0
    private var twentyFourHourClock = true
    private var cellColor = 0
    protected var menuColor = 0
    protected var lineColor = 0

    protected var isFullScreen = false
    protected var widthPadding = 0

    protected var scheduleClickListener: OnScheduleClickListener? = null
    protected var timeCellClickListener: OnTimeCellClickListener? = null
    protected var scheduleLongClickListener: OnScheduleLongClickListener? = null

    protected var border: Boolean = false
    protected var xEndLine: Boolean = false
    private var yEndLine: Boolean = false


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



    @SuppressLint("Recycle", "CustomViewStyleable")
    fun initView(context: Context, attrs: AttributeSet?) {
        tableContext = context
        val inflater = LayoutInflater.from(tableContext)
        val v = inflater.inflate(R.layout.mintable, this, false)
        addView(v)


        if (attrs == null) {
            return
        }

        val array = tableContext.obtainStyledAttributes(attrs, R.styleable.MinTimeTableView)

        radiusStyle = array.getInt(R.styleable.MinTimeTableView_radiusOption, 0)
        twentyFourHourClock = array.getBoolean(R.styleable.MinTimeTableView_setTwentyFourHourClock, true)
        cellColor = array.getColor(R.styleable.MinTimeTableView_cellColor, 0)
        menuColor = array.getColor(R.styleable.MinTimeTableView_menuColor, 0)
        lineColor = array.getColor(R.styleable.MinTimeTableView_lineColor,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                resources.getColor(R.color.default_line, null)
            else
                resources.getColor(R.color.default_line))

        border = array.getBoolean(R.styleable.MinTimeTableView_border, false)
        xEndLine = array.getBoolean(R.styleable.MinTimeTableView_xEndLine, false)
        yEndLine = array.getBoolean(R.styleable.MinTimeTableView_yEndLine, false)

        if(lineColor != 0) {
            mainTable.setBackgroundColor(lineColor)
            topMenu.setBackgroundColor(lineColor)
            leftMenu.setBackgroundColor(lineColor)
        }

        isFullScreen = array.getBoolean(R.styleable.MinTimeTableView_isFullWidth, false)
        widthPadding = array.getInteger(R.styleable.MinTimeTableView_widthPadding, 0)

        array.recycle()
    }

    protected fun calculateTime (schedules: ArrayList<ScheduleEntity>) {
        tableStartTime = getHour(schedules[0].startTime)
        tableEndTime = 16
        schedules.map {entity ->
            if(getHour(entity.startTime) < tableStartTime)
                tableStartTime = getHour(entity.startTime)
            if(getHour(entity.endTime) >= tableEndTime)
                tableEndTime = getHour(entity.endTime) + 1
        }
        if ((tableEndTime - tableStartTime) < 7) {
            tableEndTime = tableStartTime + 7
        }
    }

    protected fun recycleTimeCell () {
        for(i in 0 until (tableEndTime - tableStartTime)) {
            dayList.forEach { day -> val j: Int = dayList.indexOf(day)
                mainTable.addView(TableCellView(tableContext, cellHeightPx.roundToInt(), averageWidth, (j * averageWidth), (i * cellHeightPx.roundToInt()), cellColor, timeCellClickListener, j, (tableStartTime + i)))
            }
            val hour = if (twentyFourHourClock) (tableStartTime + i)
            else {
                if ((tableStartTime + i)!=12) (tableStartTime + i) % 12
                else (tableStartTime + i)
            }
            if (yEndLine) {
                timeCell.addView(
                    YxisView(
                        tableContext,
                        cellHeightPx.roundToInt(),
                        leftMenuWidthPx.roundToInt(),
                        hour.toString(),
                        menuColor
                    )
                )
            }
            else {
                if (i == (tableEndTime - tableStartTime) - 1) timeCell.addView(
                    YxisEndView(
                        tableContext,
                        cellHeightPx.roundToInt(),
                        leftMenuWidthPx.roundToInt(),
                        hour.toString(),
                        menuColor
                    )
                )
                else timeCell.addView(
                    YxisView(
                        tableContext,
                        cellHeightPx.roundToInt(),
                        leftMenuWidthPx.roundToInt(),
                        hour.toString(),
                        menuColor
                    )
                )
            }

        }
    }

}