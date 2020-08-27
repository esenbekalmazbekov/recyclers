package com.example.recyclerlessons.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerlessons.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        textView.text = intent.getStringExtra("name").toString()
    }
}