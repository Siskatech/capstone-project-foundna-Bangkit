package com.example.foundna_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class meowNav : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottomnavigationbar)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.background = null
        bottomNavigationView.setOnClickListener { item ->
            when (item.id) {
                R.id.home -> {
                    val intent1 = Intent(this@meowNav, dashboard::class.java)
                    startActivity(intent1)
                    true
                }
                R.id.collection -> {
                    // Handle Collection item selection
                    // Example: Navigate to CollectionActivity
                    val intent = Intent(this@meowNav, collection::class.java)
                    startActivity(intent)
                    true
                }
                R.id.searchanimal -> {
                    // Handle Search Animal item selection
                    // Example: Navigate to SearchAnimalActivity
                    val intent = Intent(this@meowNav, CameraActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.Profile -> {
                    // Handle Profile item selection
                    // Example: Navigate to ProfileActivity
                    val intent = Intent(this@meowNav, profile::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }




    }
}