package com.example.shop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.shop.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_forgot_password.*

class Forgot_password : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        supportActionBar?.hide()
        fg_reset.setOnClickListener {

            if(fg_email.text.isNullOrEmpty())
                fg_email.error="field can't be empty"
            else {




                Firebase.auth.sendPasswordResetEmail(fg_email.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this,"confirmation email was sent", Toast.LENGTH_LONG).show()
                        }
                    }}}
    }
}