package com.example.recyclerlessons.staggered

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerlessons.R
import kotlinx.android.synthetic.main.staggered_view_item.view.*

class StagAdapter : RecyclerView.Adapter<StagAdapter.StagViewHolder>() {
    private var stags = ArrayList<StagData>()

    fun updateStag(newStags : ArrayList<StagData>) {
        stags = newStags
        notifyDataSetChanged()
    }

    class StagViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: StagData) {
            itemView.apply {
                Glide.with(this.context)
                    .load(data.image)
                    .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                    .into(image)

                name.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StagViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.staggered_view_item,parent,false)
    )

    override fun onBindViewHolder(holder: StagViewHolder, position: Int) = holder.bind(stags[position])

    override fun getItemCount() = stags.size
}