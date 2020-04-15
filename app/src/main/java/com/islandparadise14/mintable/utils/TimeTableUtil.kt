package com.islandparadise14.mintable.utils

import android.content.Context
import android.graphics.Point
import android.util.TypedValue
import android.view.Display
import android.view.ViewGroup
import android.view.WindowManager

fun getHour (time: String): Int{
    val result = time.split(":")
    return result[0].toInt()
}

fun getTotalMinute (time: String): Int{
    val result = time.split(":")
    return result[0].toInt() * 60 + result[1].toInt()
}

fun dpToPx(tableContext: Context, dp: Float): Float {
    val displayMetrics = tableContext.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
}

fun getWindowWidth(context: Context): Int {
    val windowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display: Display = windowManager.defaultDisplay
    val point = Point()

    display.getSize(point)

    return point.x
}

fun removeViews(viewGroups: Array<ViewGroup>) {
    viewGroups.map { viewGroup ->
        viewGroup.removeAllViews()
    }
}