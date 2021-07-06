package com.example.shop.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class editText(context:Context,attr:AttributeSet):AppCompatEditText(context,attr) {

    init {
        applyFont()
    }

    private fun applyFont()  {
        val typeface: Typeface = Typeface.createFromAsset(context.assets,"Regular.ttf")
        setTypeface(typeface)
    }
}