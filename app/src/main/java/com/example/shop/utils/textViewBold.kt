package com.example.shop.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.EdgeEffect
import android.widget.EditText
import androidx.appcompat.widget.AppCompatTextView
import kotlinx.android.synthetic.main.activity_main.*
import java.security.AccessControlContext
import java.util.jar.Attributes

class textViewAsset(context:Context,attr:AttributeSet):AppCompatTextView(context,attr) {
    lateinit  var editText:EditText
    init {
        applyFont()
    }

    private fun applyFont()  {
        val typeface: Typeface = Typeface.createFromAsset(context.assets,"Montserrat-Bold.ttf")
        setTypeface(typeface)
    }
}