package com.example.work

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.work.databinding.ActivityMain2Binding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.login.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            if (checkAllFields()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val jay = Intent(this@MainActivity2, home::class.java)
                        startActivity(jay)
                        finish()
                    } else {
                        Log.e("error", it.exception.toString())
                    }
                }
            }

        }
        binding.create.setOnClickListener {
            val kay = Intent(this@MainActivity2, MainActivity3::class.java)
            startActivity(kay)
            finish()
        }

    }
    private fun checkAllFields():Boolean{
        val email = binding.email.text.toString()
        if (binding.email.text.toString()==""){
            binding.emailayout.error = "Please input ur Email"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailayout.error = "Please input ur email using the right format"
            return false
        }
        if (binding.password.text.toString() ==""){
            binding.passwordlayout.error = "Please enter Password"
            binding.passwordlayout.errorIconDrawable = null
            return false
        }
        if(binding.password.length() <=8){
            binding.passwordlayout.error = "Passoword is too short "
            binding.passwordlayout.errorIconDrawable = null
        }
        return true
    }
}
