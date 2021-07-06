package com.example.shop.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.shop.R
import com.example.shop.fragments.HomeFragment
import com.example.shop.fragments.SettingFragment
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView
import kotlinx.android.synthetic.main.fragment_setting.*

 class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(topToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        topToolbar.setNavigationOnClickListener { showAlertDialog() }
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.ffFragment, HomeFragment()).commit()

        }


        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)










    }
    val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.homeitem-> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.ffFragment,HomeFragment()).commit()

                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.settingItem-> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.ffFragment, SettingFragment()).commit()

                }
                return@OnNavigationItemSelectedListener true
            }




        }
        false
    }



    fun showAlertDialog()
{
    val builder = AlertDialog.Builder(this)
    builder.setTitle("Androidly Alert")
    builder.setMessage("do you want to sign out")

    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Firebase.auth.signOut()
        startActivity(Intent(this,LoginActivity::class.java))


    }

    builder.setNegativeButton(android.R.string.no) { dialog, which ->


    }


    builder.show()

}


}