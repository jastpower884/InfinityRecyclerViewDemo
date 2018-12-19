package com.jastzeonic.infinityrecyclerviewdemo.component

//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
//import com.rd.PageIndicatorView
//import com.yx.ub8.common.core.Logger
//import kotlin.math.absoluteValue
//
//class IndicatorScrollerListener(private val overlapDecoration: OverlapDecoration,
//                                private val pageIndicatorView: PageIndicatorView) : RecyclerView.OnScrollListener() {
//
//
//    private var mConsumeX = 0
//    private var mPosition = 0
//
//
//    fun updatePosition(currentPosition: Int) {
//        mPosition = currentPosition
//        mConsumeX = 0
//    }
//
//    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//        super.onScrolled(recyclerView, dx, dy)
//
//        mConsumeX += dx
//
//        recyclerView.post {
//            val shouldConsumeX = overlapDecoration.mItemConsumeX
//
//            val offset = mConsumeX.toFloat() / shouldConsumeX.toFloat()
//
//            var movementPosition = 0
//            if (offset > 0) {
//                movementPosition++
//                if ((offset.toInt() > 0)) {
//                    mPosition += offset.toInt()
//                    mConsumeX -= shouldConsumeX
//                    pageIndicatorView.setProgress((mPosition) % pageIndicatorView.count, offset)
//                    pageIndicatorView.setInteractiveAnimation(false)
//                    pageIndicatorView.setSelected((mPosition) % pageIndicatorView.count)
//                } else {
//                    movementPosition += mPosition
//
//                    pageIndicatorView.setInteractiveAnimation(true)
//                    val targetPosition = movementPosition % (pageIndicatorView.count)
//                    pageIndicatorView.setProgress(targetPosition, offset)
//                }
//
//            } else if (offset < 0) {
//                movementPosition--
//                if (offset.toInt() < 0) {
//                    mPosition += offset.toInt()
//                    mConsumeX += shouldConsumeX
//                    val targetPosition = mPosition % (pageIndicatorView.count)
//                    pageIndicatorView.setProgress(targetPosition, offset.absoluteValue)
//                    pageIndicatorView.setInteractiveAnimation(false)
//                    pageIndicatorView.setSelected(targetPosition)
//                } else {
//                    movementPosition += mPosition
//
//                    pageIndicatorView.setInteractiveAnimation(true)
//                    val targetPosition = movementPosition % (pageIndicatorView.count)
//
//                    pageIndicatorView.setProgress(targetPosition, offset.absoluteValue)
//
//                }
//            }else{
//                pageIndicatorView.setInteractiveAnimation(false)
//                pageIndicatorView.setSelected((mPosition) % pageIndicatorView.count)
//            }
//
//
//        }
//
//
//    }
//}