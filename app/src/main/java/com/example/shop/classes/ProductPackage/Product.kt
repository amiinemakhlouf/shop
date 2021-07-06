package com.example.shop.classes.ProductPackage

import android.net.Uri
import android.net.UrlQuerySanitizer
import com.example.shop.classes.Client
import org.w3c.dom.Text

open  class Product(open var idProduct: String="", open var image : Uri?=null,  open var idSeller: String="", open var description:String="", open var price:Double=0.0, open   var idbuyer: String?=null)



