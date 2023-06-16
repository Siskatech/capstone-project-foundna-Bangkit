package com.example.foundna_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.foundna_app.databinding.ActivityMainBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.sign

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    lateinit var auth : FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.buttonlogin.setOnClickListener{
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        binding.buttonlogin.setOnClickListener{
            val  email = binding.rvEmail.text.toString()
            val username = binding.rvUsername.text.toString()
            val password = binding.pw.text.toString()
            val repass = binding.repw.text.toString()

            if (email.isEmpty()){
                binding.rvEmail.error = "Email Harus Diisi"
                binding.rvEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.rvEmail.error = "Email Tidak Valid"
                binding.rvEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.pw.error = "Password Harus Diisi"
                binding.pw.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 8){
                binding.pw.error = "Password Minimal 8 Karakter"
                binding.pw.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email,username,password,repass)
        }

        val textsignup = findViewById<TextView>(R.id.signIn);

        textsignup.setOnClickListener({
            val loginpage = Intent(this, login::class.java)
            startActivity(loginpage)
        })
    }

    private fun RegisterFirebase(email: String, username: String, password: String, repass: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Register Berhasil, Login", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, login::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}


