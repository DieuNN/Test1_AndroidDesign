package com.example.dieunnph15766

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.dieunnph15766.fragment.ContactFragment
import com.example.dieunnph15766.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()

         bottom_navigation.setOnNavigationItemSelectedListener { item ->
             when (item.itemId) {
                 R.id.menu_home -> supportFragmentManager.beginTransaction()
                     .replace(R.id.fragment_container, HomeFragment()).commit()
                 R.id.menu_contact -> supportFragmentManager.beginTransaction()
                     .replace(R.id.fragment_container, ContactFragment()).commit()
             }
             true
         }
    }
}