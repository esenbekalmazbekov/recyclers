package com.example.recyclerlessons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerlessons.selecteditem.SelectedItemActivity
import com.example.recyclerlessons.simple.SimpleRecyclerActivity
import com.example.recyclerlessons.staggered.StaggeredRecyclerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listeners()
    }

    private fun listeners() {
        simple.setOnClickListener { startActivity(Intent(this,SimpleRecyclerActivity::class.java)) }
        staggered.setOnClickListener { startActivity(Intent(this,StaggeredRecyclerActivity::class.java)) }
        selectedItem.setOnClickListener { startActivity(Intent(this,SelectedItemActivity::class.java)) }
    }
}