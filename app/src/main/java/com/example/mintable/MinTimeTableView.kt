package com.example.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.Display
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager

class MinTimeTableView : LinearLayout {
    var data: ArrayList<ScheduleEntity>? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }


    @SuppressLint("Recycle")
    fun init(context: Context, attrs: AttributeSet?) {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x.toFloat()
        val height = size.y.toFloat()

        val view = LinearLayout.LayoutParams(300, LinearLayout.LayoutParams.MATCH_PARENT)
        view.leftMargin = 30

        val array = context.obtainStyledAttributes(attrs, R.styleable.MinTimeTableView)

        val topMenuHeight = array.getInteger(R.styleable.MinTimeTableView_setTopMenuHeight, 30)
        // something function

        val leftMenuWidth = array.getInteger(R.styleable.MinTimeTableView_setLeftMenuWidth, 30)
        // something function

        val cellHeight = array.getInteger(R.styleable.MinTimeTableView_setCellHeight, 60)
        // something function


    }
}
