package com.jastzeonic.infinityrecyclerviewdemo.component

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.jastzeonic.infinityrecyclerviewdemo.R
import com.jastzeonic.infinityrecyclerviewdemo.model.ImageModel
import com.yx.ub8.ui.lobby.view.component.ItemViewHolder

class InfinityRecycleViewAdapter(private val onAdViewItemClick: (adNewsId: String) -> Unit = {}) :
        RecyclerView.Adapter<ItemViewHolder>() {
    private var items: MutableList<ImageModel> = mutableListOf()

    fun setItemsToPretendInfinity(items: MutableList<ImageModel>) {
        this.items = items
    }


    override fun getItemCount(): Int {
        return if (items.size > 0) {
            Int.MAX_VALUE
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        (holder.rootView as ImageView).setImageResource(items[position % items.size].resId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.layout_ad_view_item,
                parent, false)) { position ->
            onAdViewItemClick.invoke(items[position % items.size].name)
        }
    }

}