package com.example.shop.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shop.classes.Client
import com.example.shop.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity() : AppCompatActivity() {
   private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        database = Firebase.database.reference
        supportActionBar?.hide()
        create_bt.setOnClickListener {


            if ((!fieldsAreOK()))
                Toast.makeText(this, "fields can't be empty", Toast.LENGTH_LONG).show()
            else {
                if (re_pw.text.toString().length < 8)
                    re_pw.error = "8 caracters at least needed"
                else {


                    checkEmailExistsOrNot()  //


                }
            }


        }


    }

    private fun fieldsAreOK(): Boolean {
        return (re_fn.text!!.isNotEmpty() && re_adress.text!!.isNotEmpty() && re_email.text!!.isNotEmpty() && re_pn.text!!.isNotEmpty())

    }


    private fun checkEmailExistsOrNot() {

        FirebaseAuth
            .getInstance()
            .fetchSignInMethodsForEmail(re_email.text.toString())
            .addOnCompleteListener { task ->

                Log.d(TAG, "" + task.result?.signInMethods?.size)

                if (task.result?.signInMethods?.size === 0) {
                    // email not existed
                    Toast.makeText(this, "account successfully created", Toast.LENGTH_LONG).show()
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        re_email.text.toString(),
                        re_pw.text.toString()
                    )

                    setClient()





                    @Suppress("DEPRECATION")
                    Handler().postDelayed(
                        {
                            startActivity(Intent(this, LoginActivity::class.java))


                        }, 4000
                    )


                } else {
                    // email existed
                    re_email.error = "Email already exists"


                }
            }.addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

    private fun setClient() {
       val auth = Firebase.auth
        auth.signInWithEmailAndPassword(re_email.text.toString(), re_pw.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val uid = FirebaseAuth.getInstance().currentUser!!.uid
                    val usersRef = database.child("Users")
                    usersRef.child(uid).setValue(
                        Client(
                            re_fn.text.toString(),
                            re_email.text.toString(),
                            re_pn.text.toString().toLong(),
                            re_adress.text.toString()
                        )
                    )
                    Firebase.auth.signOut()
                }






            }


}}