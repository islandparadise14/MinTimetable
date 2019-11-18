package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.mintable.view.*
import kotlin.math.roundToInt

class MinTimeTableView : LinearLayout {
    var data: ArrayList<ScheduleEntity>? = null
    var topMenuHeight: Int = 20
    var leftMenuWidth: Int = 30
    var cellHeight: Int = 50

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
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.mintable, this, false)
        addView(v)
    }

    fun update(context: Context, list: Array<String>) {

        val topMenuHeightPx = dpToPx(context, topMenuHeight.toFloat())
        val leftMenuWidthPx = dpToPx(context, leftMenuWidth.toFloat())
        val cellHeightPx = dpToPx(context, cellHeight.toFloat())

        leftMenu.layoutParams = LayoutParams(leftMenuWidthPx.roundToInt(), LayoutParams.WRAP_CONTENT)
        topMenu.layoutParams =  LayoutParams(LayoutParams.WRAP_CONTENT, topMenuHeightPx.roundToInt())

        removeViews()

        zeroPoint.addView(ZeroPointView(context, topMenuHeightPx.roundToInt(), leftMenuWidthPx.roundToInt()))


        val averageWidth = (timetable.width - leftMenuWidthPx.roundToInt())/list.size

        for(i in 0 until list.size - 1) {
            topMenu.addView(XxisView(context, topMenuHeightPx.roundToInt(), averageWidth, list[i]))
        }
        topMenu.addView(
            XxisEndView(
                context,
                topMenuHeightPx.roundToInt(),
                averageWidth,
                list[list.size - 1]
            )
        )

        for(i in 0..11) {
            timeCell.addView(YxisView(context, cellHeightPx.roundToInt(), leftMenuWidthPx.roundToInt(), (9 + i).toString()))
        }
        timeCell.addView(YxisEndView(context, cellHeightPx.roundToInt(), leftMenuWidthPx.roundToInt(), "21"))

        for(i in 0..12) {
            for(j in 0 until list.size) {
                mainTable.addView(TableCellView(context, cellHeightPx.roundToInt(), averageWidth, (j * averageWidth), (i * cellHeightPx.roundToInt())))
            }
        }
    }

    private fun dpToPx(context: Context, dp: Float): Float {
        val displayMetrics = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
    }

    private fun removeViews() {
        zeroPoint.removeAllViews()
        topMenu.removeAllViews()
        timeCell.removeAllViews()
        mainTable.removeAllViews()
    }
}
