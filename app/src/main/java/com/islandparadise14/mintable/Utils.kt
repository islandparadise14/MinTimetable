package com.islandparadise14.mintable

import android.content.Context
import android.util.TypedValue
import android.view.ViewGroup

interface Utils {
    fun getHour (time: String): Int{
        val result = time.split(":")
        return result[0].toInt()
    }

    fun getTotalMinute (time: String): Int{
        val result = time.split(":")
        return result[0].toInt() * 60 + result[1].toInt()
    }

    fun dpToPx(tableContext: Context,dp: Float): Float {
        val displayMetrics = tableContext.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
    }

    fun removeViews(viewGroups: Array<ViewGroup>) {
        viewGroups.map { viewGroup ->
            viewGroup.removeAllViews()
        }
    }
}