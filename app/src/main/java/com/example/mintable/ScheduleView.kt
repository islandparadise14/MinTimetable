package com.example.mintable

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView

class ScheduleView : LinearLayout {

    private var linearLayout: LinearLayout? = null
    private var name: TextView? = null
    private var room: TextView? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    fun setting() {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.item_schedule, this, true)

        linearLayout = findViewById(R.id.tableItem)
        name = findViewById(R.id.name)
        room = findViewById(R.id.room)

        val border = GradientDrawable()
        border.setColor(0xFFFFFF)
        border.setStroke(1, -0x1000000)
        border.cornerRadius = 0.5f
        border.cornerRadii = floatArrayOf(0.5f, 0.3f, 0.1f, 0.8f)
        linearLayout!!.background = border


        var dd = MinTimeTableView(context)
        dd.data
    }
}
