package com.example.shop.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shop.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()



        auth = Firebase.auth


        tv_fp.setOnClickListener {
            startActivity(Intent(this,Forgot_password::class.java))
            finish()
        }
      tv_register.setOnClickListener {
          startActivity(Intent(this,RegisterActivity::class.java))


      }
        tvsign_in.setOnClickListener {
            if(email.text.isNullOrEmpty() || psw.text.isNullOrEmpty() )
                Toast.makeText(this,"fields can't be empty",Toast.LENGTH_LONG).show()

              else
            {



        auth.signInWithEmailAndPassword(email.text.toString(), psw.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this,"signInWithEmail:success",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,MainActivity::class.java))



                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }}
        }







    }


}