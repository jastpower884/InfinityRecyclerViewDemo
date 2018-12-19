package com.jastzeonic.infinityrecyclerviewdemo.component

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.jastzeonic.infinityrecyclerviewdemo.toPx

class MarginDecoration : RecyclerView.ItemDecoration() {


    var mItemConsumeX = 0


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        val lp = view.layoutParams as RecyclerView.LayoutParams

        val itemWidth = parent.width - 2 * sideVisibleWidth

        mItemConsumeX = itemWidth

        if (lp.width != itemWidth) {
            lp.width = itemWidth
        }

    }

    private var sideVisibleWidth = 150

    fun getSideVisibleWidth() = sideVisibleWidth


}