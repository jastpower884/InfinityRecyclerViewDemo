package com.yx.ub8.ui.lobby.view.component

import android.support.v7.widget.RecyclerView
import android.view.View

class ItemViewHolder(val rootView: View,    private val onAdViewItemClick: (position: Int) -> Unit = {}
) : RecyclerView.ViewHolder(rootView) {
    init {

        rootView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onAdViewItemClick.invoke(adapterPosition)
            }

        }

    }
}