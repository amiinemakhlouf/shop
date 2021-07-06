package com.example.shop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.Adapters.ProductAdapter
import com.example.shop.R
import com.example.shop.classes.Client
import com.example.shop.classes.ProductPackage.Clothes
import com.example.shop.classes.ProductPackage.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_display_clothes.*
import java.lang.reflect.InvocationTargetException
import kotlin.system.exitProcess

class DisplayClothes : AppCompatActivity() {
    var database = FirebaseDatabase.getInstance().getReference("Product")
    var list = mutableListOf<Product>()
    val fireStore=Firebase.storage.reference
    var productAdapter = ProductAdapter(list)
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_clothes)
        setSupportActionBar(topToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        topToolbar.setNavigationOnClickListener {
            val i: Intent = Intent(this, AddProduct::class.java)
            i.putExtra("ProductType", "Clothes")
            startActivity(i)
            finish()
        }

        layoutManager=LinearLayoutManager(this)
        list.clear()
        database.child("Clothes").addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    for(userSnapshot in snapshot.children)
                    {
                       val clothes= userSnapshot.getValue(Product::class.java)
                        list.add(clothes!!)
                    }

                   Product_rv.layoutManager = layoutManager
                    Product_rv.adapter = productAdapter

                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


            })



    }
}

