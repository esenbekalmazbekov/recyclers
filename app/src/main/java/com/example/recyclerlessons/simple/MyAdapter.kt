package com.example.recyclerlessons.simple

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerlessons.R
import kotlinx.android.synthetic.main.my_text_view.view.*
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

class MyAdapter(private var myDataset: ArrayList<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    private val diff = BlogItemDiffCallback()
    private lateinit var diffResult : DiffUtil.DiffResult

    fun updateSubmitList(newList : ArrayList<String>) {
        diffResult = DiffUtil.calculateDiff(diff.also {
            it.oldDataset = myDataset
            it.newDataset = newList
        })
        myDataset = newList
        diffResult.dispatchUpdatesTo(this)
    }

    fun add(position: Int){
        val old = myDataset.clone() as ArrayList<String>
        diffResult = DiffUtil.calculateDiff(diff.also {
            it.oldDataset = old
            it.newDataset = myDataset.also {item->
                item.add(position,"element$position")
            }
        })
        diffResult.dispatchUpdatesTo(this)
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_text_view,parent,false)
        return MyViewHolder(view)
    }

    @Suppress("DEPRECATION")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            view.text.text = myDataset[position]

            view.image.setOnClickListener {
                diff.oldDataset = myDataset
                diff.newDataset = (myDataset.clone() as ArrayList<String>).also { it.remove(myDataset[absoluteAdapterPosition]) }
                myDataset = diff.newDataset
                diffResult = DiffUtil.calculateDiff(diff)
                diffResult.dispatchUpdatesTo(this@MyAdapter)
            }

            view.text.setOnClickListener {
                val i = Intent(it.context, DetailActivity::class.java)
                i.putExtra("name",myDataset[position])
                it.context.startActivity(i)
            }
        }
    }

    override fun getItemCount() = myDataset.size

    fun swap(fromPos: Int, toPos: Int) {
        Collections.swap(myDataset,fromPos,toPos)
        notifyItemMoved(fromPos,toPos)
    }

    fun remove(direction: Int) {
        myDataset.removeAt(direction)
        notifyDataSetChanged()
    }


    class BlogItemDiffCallback : DiffUtil.Callback() {

        lateinit var oldDataset: ArrayList<String>
        lateinit var newDataset: ArrayList<String>

        override fun getOldListSize() = oldDataset.size

        override fun getNewListSize() = newDataset.size

        // here we check the objects id
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldDataset[oldItemPosition] == newDataset[newItemPosition]

        // here if we use object we need to use equal
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldDataset[oldItemPosition] == newDataset[newItemPosition]
    }
}