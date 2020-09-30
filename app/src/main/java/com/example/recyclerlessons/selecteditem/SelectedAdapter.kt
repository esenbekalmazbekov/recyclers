package com.example.recyclerlessons.selecteditem

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import com.example.recyclerlessons.R
import com.example.recyclerlessons.selecteditem.helpers.SelectedDetails
import kotlinx.android.synthetic.main.item_selected.view.*

class SelectedAdapter : RecyclerView.Adapter<SelectedAdapter.SelectedViewHolder>() {
    private var models = ArrayList<SelectedItem>()

    lateinit var selectionTracker : SelectionTracker<Long>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SelectedViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_selected, parent, false)
    )

    override fun onBindViewHolder(holder: SelectedViewHolder, position: Int) =
        holder.bind(models[position])

    override fun getItemCount() = models.size

    fun submitList(newModels: ArrayList<SelectedItem>) {
        models = newModels
        notifyDataSetChanged()
    }

    inner class SelectedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val details = SelectedDetails()

        fun bind(item: SelectedItem) {
            itemView.apply {
                name.text = item.name
                phone.text = item.phone
            }
            details.adapterPosition = absoluteAdapterPosition
            details.item = item

            if(selectionTracker.isSelected(details.selectionKey)){
                setUiItemSelected(itemView)
                itemView.isActivated = true
            }else {
                setUiItemUnSelected(itemView)
                itemView.isActivated = false
            }
        }

        private fun setUiItemUnSelected(itemView: View) {
            itemView.setBackgroundColor(Color.MAGENTA)
        }

        private fun setUiItemSelected(itemView: View) {
            itemView.setBackgroundColor(Color.CYAN)
        }
    }
}