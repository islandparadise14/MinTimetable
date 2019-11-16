package com.islandparadise14.mintable

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.LinearLayout

class MinTimeTableView : LinearLayout {
    var data: ArrayList<ScheduleEntity>? = null
    var topMenuHeight: Int = 300
    var leftMenuWidth: Int = 300
    var cellHeight: Int = 600

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
        var v = inflater.inflate(R.layout.mintable, this, false)
        addView(v)

//        leftMenu.layoutParams = LayoutParams(leftMenuWidth, LayoutParams.MATCH_PARENT)
//        topMenu.layoutParams =  LayoutParams(LayoutParams.MATCH_PARENT, topMenuHeight)
//        zeroPoint.layoutParams = LayoutParams(leftMenuWidth, LayoutParams.MATCH_PARENT)

    }

    fun later() {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x.toFloat()
        val height = size.y.toFloat()
    }
}
