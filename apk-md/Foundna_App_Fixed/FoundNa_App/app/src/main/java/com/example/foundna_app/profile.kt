package com.example.foundna_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class profile : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val logout = findViewById<TextView>(R.id.action_logout)

// Tambahkan listener pada tombol
        logout.setOnClickListener {
            // Buat intent untuk beralih ke activity lain
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }



    }



}