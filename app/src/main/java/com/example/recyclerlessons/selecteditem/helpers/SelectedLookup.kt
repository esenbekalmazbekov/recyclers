package com.example.recyclerlessons.selecteditem.helpers

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerlessons.selecteditem.SelectedAdapter

class SelectedLookup(private val rv : RecyclerView) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = rv.findChildViewUnder(event.x,event.y)
        if (view != null) {
            val holder = rv.getChildViewHolder( view )

            return (holder as SelectedAdapter.SelectedViewHolder).details
        }

        return null
    }
}