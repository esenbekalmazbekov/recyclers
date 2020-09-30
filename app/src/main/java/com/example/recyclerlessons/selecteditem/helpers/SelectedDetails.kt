package com.example.recyclerlessons.selecteditem.helpers

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import com.example.recyclerlessons.selecteditem.SelectedItem

class SelectedDetails : ItemDetailsLookup.ItemDetails<Long>() {
    var item: SelectedItem? = null
    var adapterPosition: Int = -1

    override fun getPosition(): Int = adapterPosition

    override fun getSelectionKey() = item!!.id.toLong()

    override fun inSelectionHotspot(e: MotionEvent) = true
}