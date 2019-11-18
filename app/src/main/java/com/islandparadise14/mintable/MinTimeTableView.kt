package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.mintable.view.*
import kotlin.math.roundToInt

class MinTimeTableView : LinearLayout {
    var data: ArrayList<ScheduleEntity>? = null
    var topMenuHeight: Int = 20
    var leftMenuWidth: Int = 30
    var cellHeight: Int = 50
    private var tableContext: Context? = null

    private var topMenuHeightPx: Float? = null
    private var leftMenuWidthPx: Float? = null
    private var cellHeightPx: Float? = null
    private var averageWidth: Int = 0

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

        array.recycle()
    }

    fun baseSetting(topMenuHeight: Int?, leftMenuWidth: Int?, cellHeight: Int?) {
        if(topMenuHeight != null) this.topMenuHeight = topMenuHeight
        if(leftMenuWidth != null) this.leftMenuWidth = leftMenuWidth
        if(cellHeight != null) this.cellHeight = cellHeight
    }

    fun initTable(dayList: Array<String>) {

        topMenuHeightPx = dpToPx(topMenuHeight.toFloat())
        leftMenuWidthPx = dpToPx(leftMenuWidth.toFloat())
        cellHeightPx = dpToPx(cellHeight.toFloat())

        leftMenu.layoutParams = LayoutParams(leftMenuWidthPx!!.roundToInt(), LayoutParams.WRAP_CONTENT)
        topMenu.layoutParams =  LayoutParams(LayoutParams.WRAP_CONTENT, topMenuHeightPx!!.roundToInt())
        mainTable.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

        removeViews()

        zeroPoint.addView(ZeroPointView(tableContext!!, topMenuHeightPx!!.roundToInt(), leftMenuWidthPx!!.roundToInt()))


        averageWidth = (timetable.width - leftMenuWidthPx!!.roundToInt())/dayList.size

        for(i in 0 until dayList.size) {
            if(i == dayList.size - 1) topMenu.addView(
                XxisEndView(
                    tableContext!!,
                    topMenuHeightPx!!.roundToInt(),
                    averageWidth,
                    dayList[dayList.size - 1]
                )
            )
            else topMenu.addView(XxisView(tableContext!!, topMenuHeightPx!!.roundToInt(), averageWidth, dayList[i]))
        }

        for(i in 0 until 12) {
            for(j in 0 until dayList.size) {
                mainTable.addView(TableCellView(tableContext!!, cellHeightPx!!.roundToInt(), averageWidth, (j * averageWidth), (i * cellHeightPx!!.roundToInt())))
            }
            if(i == 11) timeCell.addView(YxisEndView(tableContext!!, cellHeightPx!!.roundToInt(), leftMenuWidthPx!!.roundToInt(), (9 + i).toString()))
            else timeCell.addView(YxisView(tableContext!!, cellHeightPx!!.roundToInt(), leftMenuWidthPx!!.roundToInt(), (9 + i).toString()))
        }
    }

    fun addSchedules(schedules: ArrayList<ScheduleEntity>) {
        schedules.map {entity ->
            if(entity.originId != null && entity.scheduleDay != null && entity.startTime != null && entity.endTime != null)
                mainTable.addView(
                    ScheduleView(
                        tableContext!!,
                        entity.originId!!,
                        entity.scheduleName,
                        entity.roomInfo,
                        entity.scheduleDay!!,
                        entity.startTime!!,
                        entity.endTime!!,
                        entity.backgroundColor,
                        entity.textColor,
                        cellHeightPx!!.roundToInt(),
                        averageWidth,
                        entity.onClick
                    )
                )
        }
    }

    private fun dpToPx(dp: Float): Float {
        val displayMetrics = tableContext!!.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
    }

    private fun removeViews() {
        zeroPoint.removeAllViews()
        topMenu.removeAllViews()
        timeCell.removeAllViews()
        mainTable.removeAllViews()
    }
}
