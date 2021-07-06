package com.example.shop.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shop.R
import com.example.shop.classes.ProductPackage.Product
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_product.view.*
import kotlinx.android.synthetic.main.items.view.*

class ProductAdapter(private val  dataSEt:MutableList<Product>,
): RecyclerView.Adapter<ProductAdapter.ProduitViewHolder> ()  {
    class ProduitViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView)
    var database=Firebase.database.reference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProduitViewHolder {
        return ProduitViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false))

    }
    override fun onBindViewHolder(holder: ProduitViewHolder, position: Int) {
        val  p=dataSEt[position]
        holder.itemView.apply { tv_desc1.text=p.description
            tv_price1.text=p.price.toString()

        }

    }

    override fun getItemCount(): Int {
        return dataSEt.size
    }

}