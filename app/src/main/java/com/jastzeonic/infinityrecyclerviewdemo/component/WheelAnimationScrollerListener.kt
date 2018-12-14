package com.yx.ub8.ui.lobby.view.component

import android.support.v7.widget.RecyclerView

class WheelAnimationScrollerListener(private val overlapDecoration: OverlapDecoration) : RecyclerView.OnScrollListener() {

    private var mConsumeX = 0
    private var mPosition = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        onHorizontalScroll(recyclerView, dx)
    }

    fun updateConsume(currentPosition: Int) {
        mPosition = currentPosition
        mConsumeX = 0
    }

    fun getCurrentPosition() = mPosition


    private fun onHorizontalScroll(recyclerView: RecyclerView, dx: Int) {
        mConsumeX += dx

        recyclerView.post {
            val shouldConsumeX = overlapDecoration.mItemConsumeX

            // 位置移動數值(-1 - 0 - 1) =（單一物件距離(-(單一物件長度)-(單一物件長度) / 單一物件長度
            val offset = mConsumeX.toFloat() / shouldConsumeX.toFloat()


            // 获取当前页移动的百分值
            val percent = if (offset > 0) {
                offset - offset.toInt()
            } else {
                1f + offset
            }

            var movementPosition = 0
            if (offset > 0) {
                if ((offset.toInt() > 0)) {
                    mPosition += offset.toInt()
                    mConsumeX -= shouldConsumeX
                    movementPosition = mPosition
                } else {
                    movementPosition += mPosition
                }

            } else if (offset < 0) {
                movementPosition--
                if (offset.toInt() < 0) {
                    mPosition += offset.toInt()
                    mConsumeX += shouldConsumeX
                    movementPosition = mPosition
                } else {
                    movementPosition += mPosition
                }
            }



            setBottomToTopAnim(recyclerView, movementPosition, percent)
        }

    }

    private val mAnimFactor = 0.2f

    fun setBottomToTopAnim(recyclerView: RecyclerView, position: Int, percent: Float) {
        // 中間頁面
        val mCurView = recyclerView.layoutManager!!.findViewByPosition(position)
        // 右邊頁面
        val mRightView = recyclerView.layoutManager!!.findViewByPosition(position + 1)
        // 左邊頁面
        val mLeftView = recyclerView.layoutManager!!.findViewByPosition(position - 1)
        // 右右邊頁面，再向右滑的時候會出現
        val mRRView = recyclerView.layoutManager!!.findViewByPosition(position + 2)

        val twoSideValue = 1 - mAnimFactor + percent * mAnimFactor

        val currentItemsScaleValue = 1 - percent * mAnimFactor

        if (mLeftView != null) {
            mLeftView.scaleX = twoSideValue
            mLeftView.scaleY = twoSideValue

            mLeftView.translationY = (mLeftView.bottom - mLeftView.top) * 0.1f - ((mLeftView.bottom - mLeftView.top) * 0.1f * percent)

        }
        if (mCurView != null) {
            mCurView.scaleX = currentItemsScaleValue
            mCurView.scaleY = currentItemsScaleValue
            mCurView.translationY = ((mCurView.bottom - mCurView.top) * 0.1f) * percent
        }
        if (mRightView != null) {
            mRightView.scaleX = twoSideValue
            mRightView.scaleY = twoSideValue
            mRightView.translationY = (mRightView.bottom - mRightView.top) * 0.1f - ((mRightView.bottom - mRightView.top) * 0.1f * percent)
        }
        if (mRRView != null) {
            mRRView.scaleX = currentItemsScaleValue
            mRRView.scaleY = currentItemsScaleValue
            mRRView.translationY = ((mRRView.bottom - mRRView.top) * 0.1f) * percent
        }
    }


}