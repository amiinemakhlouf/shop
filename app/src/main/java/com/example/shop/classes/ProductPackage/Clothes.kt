package com.example.shop.classes.ProductPackage

import android.net.Uri

data class Clothes(override var idProduct:String, override var image : Uri?=null, override  var idSeller: String, override var description:String, override var price:Double, override  var idbuyer: String?=null) :
    Product(idProduct,image,idSeller,description,price,idbuyer) {


}