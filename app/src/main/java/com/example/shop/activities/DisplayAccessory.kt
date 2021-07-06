package com.example.shop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shop.R
import kotlinx.android.synthetic.main.activity_display_clothes.*

class DisplayAccessory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_accessory)
        setSupportActionBar(topToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        topToolbar.setNavigationOnClickListener {
            val  i:Intent= Intent(this,AddProduct::class.java)
            i.putExtra("ProductType","Accessory")
            startActivity(i)

        }

    }}