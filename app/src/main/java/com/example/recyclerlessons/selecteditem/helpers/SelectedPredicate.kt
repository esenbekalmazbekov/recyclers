package com.example.recyclerlessons.selecteditem.helpers

import androidx.recyclerview.selection.SelectionTracker

class SelectedPredicate : SelectionTracker.SelectionPredicate<Long>() {

    override fun canSetStateForKey(key: Long, nextState: Boolean) = true

    override fun canSetStateAtPosition(position: Int, nextState: Boolean) = true

    override fun canSelectMultiple() = true
}