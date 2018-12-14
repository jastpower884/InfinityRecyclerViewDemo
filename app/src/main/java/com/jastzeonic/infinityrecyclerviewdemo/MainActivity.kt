package com.jastzeonic.infinityrecyclerviewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import com.jastzeonic.infinityrecyclerviewdemo.component.InfinityRecycleViewAdapter
import com.jastzeonic.infinityrecyclerviewdemo.model.ImageModel
import com.yx.ub8.ui.lobby.view.component.OverlapDecoration
import com.yx.ub8.ui.lobby.view.component.WheelAnimationScrollerListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = InfinityRecycleViewAdapter()

        val list = getImageList()

        adapter.setItemsToPretendInfinity(list)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val overlapDecoration = OverlapDecoration()
        val galleryScrollerListener = WheelAnimationScrollerListener(overlapDecoration)


        recyclerView.adapter = adapter
        recyclerView.layoutManager = linearLayoutManager


        recyclerView.addItemDecoration(overlapDecoration)

        recyclerView.addOnScrollListener(galleryScrollerListener)


        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)


        val currentPosition = recyclerView.adapter!!.itemCount / 2 -
                ((recyclerView.adapter!!.itemCount / 2) % list.size)

        val offset = overlapDecoration.getItemMarginPixel()


        linearLayoutManager.scrollToPositionWithOffset(currentPosition, offset)

        recyclerView.post {
            galleryScrollerListener.setBottomToTopAnim(recyclerView, currentPosition, 0.0f)
            galleryScrollerListener.updateConsume(currentPosition)

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
