package com.example.recyclerlessons.staggered

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerlessons.R

class CustomAnimator : DefaultItemAnimator() {
    override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
        holder?.itemView?.animation = AnimationUtils.loadAnimation(holder?.itemView?.context, R.anim.item_animation_from_rigth)
        return super.animateAdd(holder)
    }

    override fun animateRemove(holder: RecyclerView.ViewHolder?): Boolean {
        holder?.itemView?.animation = AnimationUtils.loadAnimation(holder?.itemView?.context, R.anim.item_animation_to_right)
        return super.animateRemove(holder)
    }

    override fun getAddDuration(): Long {
        return 20
    }

    override fun getRemoveDuration(): Long {
        return 500
    }
}