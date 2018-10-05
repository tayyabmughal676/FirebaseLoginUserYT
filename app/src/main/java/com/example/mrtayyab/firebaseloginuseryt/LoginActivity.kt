package com.example.mrtayyab.firebaseloginuseryt

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var mLoginbtn  : Button
    lateinit var mLoginRegisterBtn : Button
    lateinit var mLoginEmail : EditText
    lateinit var mLoginPassword : EditText
    lateinit var mProgressbar : ProgressDialog

    lateinit var mAuth  : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLoginbtn = findViewById(R.id.LoginBtn)
        mLoginRegisterBtn = findViewById(R.id.LoginRegisterBtn)

        mLoginEmail = findViewById(R.id.LoginEmail)
        mLoginPassword = findViewById(R.id.LoginPassword)
        mProgressbar = ProgressDialog(this)

        mAuth = FirebaseAuth.getInstance()

        mLoginbtn.setOnClickListener {
            val email = mLoginEmail.text.toString().trim()
            val password = mLoginPassword.text.toString().trim()

            if(TextUtils.isEmpty(email)){
                mLoginEmail.error = " Enter Email"
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(password)){
                mLoginEmail.error = " Enter Password"
                return@setOnClickListener
            }

            loginUser(email , password)
        }

        mLoginRegisterBtn.setOnClickListener {
            val registerActivity = Intent(applicationContext , RegisterActivity::class.java)
            startActivity(registerActivity)
            finish()
        }

    }

    private fun loginUser(email: String, password: String) {
        mProgressbar.setMessage("Please wait..")
        mProgressbar.show()

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        mProgressbar.dismiss()
                        val startIntent  = Intent(applicationContext , MainActivity::class.java)
                        startActivity(startIntent)
                        finish()
                    } else {

                        Toast.makeText(this, "Authentication failed.${task.exception}", Toast.LENGTH_SHORT).show()

                    }

                    mProgressbar.dismiss()
                }
    }
}
