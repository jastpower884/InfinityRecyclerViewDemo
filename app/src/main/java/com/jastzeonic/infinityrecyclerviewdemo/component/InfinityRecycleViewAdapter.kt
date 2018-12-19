package com.jastzeonic.infinityrecyclerviewdemo.component

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.jastzeonic.infinityrecyclerviewdemo.R
import com.jastzeonic.infinityrecyclerviewdemo.model.ImageModel

class InfinityRecycleViewAdapter(var items: MutableList<ImageModel>) :
        RecyclerView.Adapter<ItemViewHolder>() {


    override fun getItemCount(): Int {
        return if (items.size > 0) {
            Int.MAX_VALUE
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        (holder.view as ImageView).setImageResource(items[position % items.size].resId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.image_view_item, parent, false)
        )
    }

}