package com.yx.ub8.ui.lobby.view.component

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.jastzeonic.infinityrecyclerviewdemo.toPx

class OverlapDecoration : RecyclerView.ItemDecoration() {


    var mItemConsumeX = 0


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        onSetHorizontalParams(parent, view, position, itemCount)
    }

    private var mPageMargin = 0
    private var mLeftPageVisibleWidth = 50
    private var itemWidth = 0

    fun getItemMarginPixel() = (mPageMargin).toPx() + (mLeftPageVisibleWidth).toPx()

    private fun onSetHorizontalParams(parent: ViewGroup, itemView: View?, position: Int, itemCount: Int) {
        itemWidth = parent.width
        val itemNewWidth = parent.width - ((4 * mPageMargin + 2 * mLeftPageVisibleWidth).toPx())
        val itemNewHeight = parent.height

        mItemConsumeX = itemNewWidth + (2 * mPageMargin).toPx()

        val leftMargin = if (position == 0) {
            (mLeftPageVisibleWidth + 2 * mPageMargin).toPx()
        } else {
            (mPageMargin).toPx()
        }
        val rightMargin = if (position == itemCount - 1) {
            (mLeftPageVisibleWidth + 2 * mPageMargin).toPx()
        } else {
            (mPageMargin).toPx()
        }

        setLayoutParams(itemView, leftMargin, 0, rightMargin, 0, itemNewWidth, itemNewHeight)
    }

    private fun setLayoutParams(itemView: View?, left: Int, top: Int, right: Int, bottom: Int, itemWidth: Int, itemHeight: Int) {

        val lp = itemView?.layoutParams as RecyclerView.LayoutParams
        var mMarginChange = false
        var mWidthChange = false
        var mHeightChange = false

        if (lp.leftMargin != left || lp.topMargin != top || lp.rightMargin != right || lp.bottomMargin != bottom) {
            lp.setMargins(left, top, right, bottom)
            mMarginChange = true
        }
        if (lp.width != itemWidth) {
            lp.width = itemWidth
            mWidthChange = true
        }
        if (lp.height != itemHeight) {
            lp.height = itemHeight
            mHeightChange = true

        }

        if (mWidthChange || mMarginChange || mHeightChange) {
            itemView.layoutParams = lp
        }
    }

}