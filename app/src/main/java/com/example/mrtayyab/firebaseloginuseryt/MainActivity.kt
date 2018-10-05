package com.example.mrtayyab.firebaseloginuseryt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var mAuth : FirebaseAuth
    lateinit var mLogoutBtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        mLogoutBtn = findViewById(R.id.MainLogoutBtn)

        mLogoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val startIntent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(startIntent)
            finish()

        }
    }

    override fun onStart() {
        super.onStart()

        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser == null){

            val intent = Intent(applicationContext , LoginActivity::class.java)
            startActivity(intent)
            finish()

        }else{
                Toast.makeText(applicationContext , "Login Successfully " , Toast.LENGTH_SHORT).show()
        }
    }
}
