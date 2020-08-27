package com.example.recyclerlessons.simple

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recyclerlessons.R
import com.example.recyclerlessons.staggered.CustomAnimator
import kotlinx.android.synthetic.main.activity_simple.*
import java.util.*
import java.util.Collections.swap
import java.util.stream.Collectors


class SimpleRecyclerActivity : AppCompatActivity() {
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)

        init()
        listeners()
    }

    private fun listeners() {
        fall.setOnClickListener { fallLayoutAnimation() }
        right.setOnClickListener { rightLayoutAnimation() }
        bottom.setOnClickListener { bottomLayoutAnimation() }
        scroll.setOnClickListener { scrollLayoutAnimation() }
        add.setOnClickListener { add_layout.visibility = View.VISIBLE }
        add_btn.setOnClickListener {
            add_layout.visibility = View.GONE
            my_recycler_view.itemAnimator = CustomAnimator()
            @Suppress("DEPRECATION")
            Handler().postDelayed({
                adapter.add(text.text.toString().toInt())
            }, 2 * 500)
        }
        cancel.setOnClickListener {
            add_layout.visibility = View.GONE
        }
    }

    private fun init() {
        my_recycler_view.layoutManager = LinearLayoutManager(this)
        my_recycler_view.setHasFixedSize(true)
        adapter = MyAdapter(createData())

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        my_recycler_view.addItemDecoration(divider)

        my_recycler_view.adapter = adapter

        helper()
    }

    private fun helper() = ItemTouchHelper(
        object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): Boolean {
                val fromPos = viewHolder.absoluteAdapterPosition
                val toPos = target.absoluteAdapterPosition

                adapter.swap(fromPos,toPos)

                Log.e("from", fromPos.toString())
                Log.e("to", toPos.toString())


                return true
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                adapter.remove(viewHolder.absoluteAdapterPosition)
            }
        }).also {
        it.attachToRecyclerView(my_recycler_view)
    }


    private fun fallLayoutAnimation() {
        val animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
        my_recycler_view.layoutAnimation = animation
        my_recycler_view.scheduleLayoutAnimation()
        adapter.notifyDataSetChanged()
    }

    private fun rightLayoutAnimation() {
        val animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_from_right)
        my_recycler_view.layoutAnimation = animation
        my_recycler_view.scheduleLayoutAnimation()
        adapter.notifyDataSetChanged()
    }

    private fun bottomLayoutAnimation() {
        val animation = AnimationUtils.loadLayoutAnimation(
            this,
            R.anim.layout_animation_from_bottom
        )
        my_recycler_view.layoutAnimation = animation
        my_recycler_view.scheduleLayoutAnimation()
        adapter.notifyDataSetChanged()
    }

    private fun scrollLayoutAnimation() {
        val animation = AnimationUtils.loadLayoutAnimation(
            this,
            R.anim.layout_animation_from_bottom
        )
        my_recycler_view.layoutAnimation = animation
        my_recycler_view.itemAnimator = CustomAnimator()
        my_recycler_view.scheduleLayoutAnimation()
        adapter.notifyDataSetChanged()
    }

    private fun newData() = arrayListOf(
        "element1",
        "element2",
        "element3",
        "element4",
        "element5",
        "element6",
        "element7",
        "element8",
        "element9",
        "element10",
        "element11",
        "element12",
        "element13",
        "element14",
        "element15",
        "element16",
        "element17",
        "element18",
        "element19",
        "element20",
        "element21",
        "element21.5",
        "element23"
    )

    private fun createData() = arrayListOf(
        "element1",
        "element2",
        "element3",
        "element4",
        "element5",
        "element6",
        "element7",
        "element8",
        "element9",
        "element10",
        "element11",
        "element12",
        "element13",
        "element14",
        "element15",
        "element16",
        "element17",
        "element18",
        "element19",
        "element20",
        "element21",
        "element22",
        "element23",
        "element24",
        "element25",
        "element26",
    )
}