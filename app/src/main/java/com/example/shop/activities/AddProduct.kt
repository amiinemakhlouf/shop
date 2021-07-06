package com.example.shop.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.shop.R
import com.example.shop.classes.ProductPackage.Clothes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_add_product.*

class AddProduct : AppCompatActivity() {
    val dataFire = Firebase.database.reference
    val auth = FirebaseAuth.getInstance()
    val storage = Firebase.storage.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        val idUser = auth.currentUser?.uid
        addImageButton.setOnClickListener {
            val gallery = Intent(
                Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
            startActivityForResult(gallery, 1)

        }

        tv_add.setOnClickListener {
            if (isEmpty())
                Toast.makeText(this, " fields can(t be empty", Toast.LENGTH_LONG).show()
            else {

                val ProductType: String = intent.getStringExtra("ProductType")!!


                val id: String? = dataFire.child("Product").child(ProductType).push().key
                dataFire.child("Product").child(ProductType).child(id!!).setValue(
                    Clothes(
                        id,
                        null,
                        idUser!!,
                        tv_desc.text.toString(),
                        tv_price.text.toString().toDouble()
                    )
                )
                finish()


            }
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1) {
            addImageButton.isVisible = false

            iv_product.isVisible = true
            iv_product.setImageURI(data?.data)
            storage.child("Clothes").putFile(data?.data!!)
        }

    }


    private fun isEmpty(): Boolean {

        return (tv_desc.text.isNullOrEmpty() || tv_price.text.isNullOrEmpty()||(!iv_product.isVisible))
    }
}