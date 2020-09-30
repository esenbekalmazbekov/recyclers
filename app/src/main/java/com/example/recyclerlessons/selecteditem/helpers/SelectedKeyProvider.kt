package com.example.recyclerlessons.selecteditem.helpers

import androidx.recyclerview.selection.ItemKeyProvider
import com.example.recyclerlessons.selecteditem.SelectedItem

class SelectedKeyProvider(private val selectedItems: ArrayList<SelectedItem>) : ItemKeyProvider<Long>(SCOPE_MAPPED) {

    override fun getKey(position: Int): Long = selectedItems[position].id.toLong()

    override fun getPosition(key: Long): Int = selectedItems.indexOf(
        selectedItems.single { item -> item.id.toLong() == key }
    )
}