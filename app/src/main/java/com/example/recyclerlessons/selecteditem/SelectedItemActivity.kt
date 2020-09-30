package com.example.recyclerlessons.selecteditem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerlessons.R
import com.example.recyclerlessons.selecteditem.helpers.SelectedKeyProvider
import com.example.recyclerlessons.selecteditem.helpers.SelectedLookup
import com.example.recyclerlessons.selecteditem.helpers.SelectedPredicate
import kotlinx.android.synthetic.main.activity_selected_item.*
import java.util.ArrayList

class SelectedItemActivity : AppCompatActivity() {
    private val adapter = SelectedAdapter()
    private lateinit var selectionTracker: SelectionTracker<Long>
    private val SELECTION_TRACKER_KEY = "SELECTION_TRACKER_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_item)

        init()

        configSelectionTracker(savedInstanceState)
    }

    private fun init() {
        initRV()
        initListeners()
    }

    private fun initListeners() {
        toLogSelected.setOnClickListener {
            val selected = ArrayList<SelectedItem>()
            selectionTracker.selection.forEach {
                selected.add(data.single { item -> item.id.toLong() == it})
            }

            selected.forEach {
                Log.e(it.id.toString(),it.name)
            }
        }
    }

    private fun initRV() {
        selectedRecycler.layoutManager = LinearLayoutManager(this)
        selectedRecycler.adapter = adapter
        selectedRecycler.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        adapter.submitList(data)
        adapter.notifyDataSetChanged()
    }


    private fun configSelectionTracker(savedInstanceState: Bundle?) {
        selectionTracker = SelectionTracker.Builder(
            SELECTION_TRACKER_KEY,
            selectedRecycler,
            SelectedKeyProvider(data),
            SelectedLookup(selectedRecycler),
            StorageStrategy.createLongStorage()
        )
        .withSelectionPredicate(SelectedPredicate())
        .build()

        selectionTracker.select(5.toLong())

        (selectedRecycler.adapter as SelectedAdapter).selectionTracker = selectionTracker

        if(savedInstanceState != null) {
            selectionTracker.onRestoreInstanceState(savedInstanceState)
        }
    }

    private val data = ArrayList<SelectedItem>().also{
        var i = 0
        while (i < 20){
            it.add(
                SelectedItem(
                    i,
                    "Person$i",
                    "+996 26 56 ${if (i < 10) "0$i" else i}"
                )
            )
            i++
        }
    }
}