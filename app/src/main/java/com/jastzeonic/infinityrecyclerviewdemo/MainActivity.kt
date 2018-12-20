package com.jastzeonic.infinityrecyclerviewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import com.jastzeonic.infinityrecyclerviewdemo.component.IndicatorScrollerListener
import com.jastzeonic.infinityrecyclerviewdemo.component.InfinityRecycleViewAdapter
import com.jastzeonic.infinityrecyclerviewdemo.model.ImageModel
import com.jastzeonic.infinityrecyclerviewdemo.component.MarginDecoration
import com.jastzeonic.infinityrecyclerviewdemo.component.WheelAnimationScrollerListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = getImageList()

        val adapter = InfinityRecycleViewAdapter(list)


        val marginDecoration = MarginDecoration()

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)


        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(marginDecoration)
        val currentPosition = recyclerView.adapter!!.itemCount / 2

        val offset = marginDecoration.getSideVisibleWidth()

        linearLayoutManager.scrollToPositionWithOffset(currentPosition, offset)
        pageIndicatorView.count = list.size
        recyclerView.addOnScrollListener(IndicatorScrollerListener(marginDecoration, pageIndicatorView))


        recyclerView.post {
            val galleryScrollerListener = WheelAnimationScrollerListener(marginDecoration.mItemConsumeX)

            recyclerView.addOnScrollListener(galleryScrollerListener)

            galleryScrollerListener.setBottomToTopAnim(recyclerView, currentPosition, 0f)

            galleryScrollerListener.updatePosition(currentPosition)

        }

    }

    private fun getImageList() = mutableListOf(
        ImageModel("A", R.drawable.photo_1),
        ImageModel("B", R.drawable.photo_2),
        ImageModel("C", R.drawable.photo_3),
        ImageModel("D", R.drawable.photo_4),
        ImageModel("E", R.drawable.photo_5)

    )


}
