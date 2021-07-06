package com.example.shop.classes.ProductPackage

import android.net.Uri

class Accessory(
    override var idProduct: String,
    override var image: Uri?,
    override var idSeller: String,
    override var description: String,
    override var price: Double
) : Product(idProduct, image, idSeller, description, price) {
}