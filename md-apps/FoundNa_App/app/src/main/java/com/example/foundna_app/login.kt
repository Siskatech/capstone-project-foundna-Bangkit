package com.example.foundna_app

import android.app.Activity
import android.content.ContentProviderClient
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import com.example.foundna_app.databinding.ActivityLoginBinding
import com.example.foundna_app.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class login : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityLoginBinding
    lateinit var auth : FirebaseAuth
    private lateinit var btntext: TextView

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        btntext = findViewById(R.id.letsjoin)
        btntext.setOnClickListener(this)

        binding.buttonLogintoDashboard.setOnClickListener{
            val  email = binding.emailLogin.text.toString()
            val password = binding.passwordLogin.text.toString()

            if (email.isEmpty()){
                binding.emailLogin.error = "Email Harus Diisi"
                binding.emailLogin.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.emailLogin.error = "Email Tidak Valid"
                binding.emailLogin.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.passwordLogin.error = "Password Harus Diisi"
                binding.passwordLogin.requestFocus()
                return@setOnClickListener
            }
            if (password.length < 8){
                binding.passwordLogin.error = "Password Minimal 8 Karakter"
                binding.passwordLogin.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email,password)
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        findViewById<CardView>(R.id.signingoogle).setOnClickListener{
            signInGoogle()
        }

    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
                if (result.resultCode == Activity.RESULT_OK){

                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    handleResults(task)
                }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val  account : GoogleSignInAccount? = task.result
            if (account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener{
            if (it.isSuccessful){
                val intent : Intent = Intent(this, dashboard::class.java )
                intent.putExtra("email", account.email)
                intent.putExtra("name", account.displayName)
                startActivity(intent)
            }else{
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Selamat datang di FoundNa $email", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, dashboard::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }


    //menuju halaman signup
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.letsjoin ->{
                    val intentSignup = Intent(this@login, MainActivity::class.java)
                    startActivity(intentSignup)
                }
            }
        }
    }



}