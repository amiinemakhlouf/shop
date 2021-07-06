package com.example.shop.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.example.shop.R
import com.example.shop.classes.Client
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_setting.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [settingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment(R.layout.fragment_setting) {
    private var param1: String? = null
    private var param2: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        val rootRef = FirebaseDatabase.getInstance().reference
        val usersRef = rootRef.child("Users")

        if (uid != null) {
            usersRef.child(uid).addListenerForSingleValueEvent(valueEventListener)

        } else {
            Toast.makeText(requireContext(),"didn't dind",Toast.LENGTH_SHORT).show()
        }
        upbutton.setOnClickListener {
            val uid = Firebase.auth.currentUser!!.uid
            val user =mapOf<String,Any>(
                "name" to et_fn.text.toString(),
                 "phoneNumber" to et_pn.text.toString().toLong(),
               "adresse" to et_adresse.text.toString())
            usersRef.child(uid).updateChildren(user)
            Toast.makeText(requireContext(),"Successfuly Updated",Toast.LENGTH_SHORT).show()

        }






    }

    private val valueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val name = dataSnapshot.child("name").getValue(String::class.java)
            val phoneNumber = dataSnapshot.child("phoneNumber").getValue(Long::class.java)
            val adresse = dataSnapshot.child("adresse").getValue(String::class.java)

            et_fn.setText(name)
            et_adresse.setText(adresse)
            et_pn.setText(phoneNumber.toString())
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.d("TAG", databaseError.getMessage()) //Don't ignore errors!

        }
    }

}

