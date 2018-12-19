package com.jastzeonic.infinityrecyclerviewdemo.component

import android.support.v7.widget.RecyclerView

class WheelAnimationScrollerListener(private val itemWith: Int) : RecyclerView.OnScrollListener() {

    private var scrolledWidth = 0
    private var mPosition = 0
    private val animationValue = 0.2f


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)


        setScrollInfo(recyclerView, dx)

    }

    fun updatePosition(currentPosition: Int) {
        mPosition = currentPosition
        scrolledWidth = 0
    }

    private fun setScrollInfo(recyclerView: RecyclerView, dx: Int) {
        scrolledWidth += dx

            // 位置移動數值(-1 - 0 - 1) =（單一物件距離(-(單一物件長度)-(單一物件長度) / 單一物件長度
            val offset = scrolledWidth.toFloat() / itemWith.toFloat()

            val percent = if (offset > 0) {
                offset - offset.toInt()
            } else {
                1f + offset
            }

            var movementPosition = 0
            if (offset > 0) {
                if ((offset.toInt() > 0)) {
                    mPosition += offset.toInt()
                    scrolledWidth -= itemWith
                    movementPosition = mPosition
                } else {
                    movementPosition += mPosition
                }

            } else if (offset < 0) {
                movementPosition--
                if (offset.toInt() < 0) {
                    mPosition += offset.toInt()
                    scrolledWidth += itemWith
                    movementPosition = mPosition
                } else {
                    movementPosition += mPosition
                }
            }

            setBottomToTopAnim(recyclerView, movementPosition, percent)

    }


    fun setBottomToTopAnim(recyclerView: RecyclerView, position: Int, percent: Float) {
        // 中間頁面
        val mCurView = recyclerView.layoutManager!!.findViewByPosition(position)
        // 右邊頁面
        val mRightView = recyclerView.layoutManager!!.findViewByPosition(position + 1)
        // 左邊頁面
        val mLeftView = recyclerView.layoutManager!!.findViewByPosition(position - 1)
        // 右右邊頁面，再向右滑的時候會出現
        val mRRView = recyclerView.layoutManager!!.findViewByPosition(position + 2)

        val sideTransferValue = 1 - animationValue + percent * animationValue

        val currentItemsScaleValue = 1 - percent * animationValue

        if (mLeftView != null) {
            mLeftView.scaleX = sideTransferValue
            mLeftView.scaleY = sideTransferValue
        }
        if (mCurView != null) {
            mCurView.scaleX = currentItemsScaleValue
            mCurView.scaleY = currentItemsScaleValue
        }
        if (mRightView != null) {
            mRightView.scaleX = sideTransferValue
            mRightView.scaleY = sideTransferValue
        }
        if (mRRView != null) {
            mRRView.scaleX = currentItemsScaleValue
            mRRView.scaleY = currentItemsScaleValue
        }

    }


}