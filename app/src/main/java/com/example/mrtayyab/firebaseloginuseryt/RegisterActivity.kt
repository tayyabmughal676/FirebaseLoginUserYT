package com.example.mrtayyab.firebaseloginuseryt

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var mRegisterBtn : Button
    lateinit var mRegisterEmail : EditText
    lateinit var mRegisterPassword : EditText
    lateinit var mRegisterLoginBtn : Button
    lateinit var mProgressbar : ProgressDialog

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegisterBtn = findViewById(R.id.RegisterBtn)
        mRegisterLoginBtn = findViewById(R.id.RegisterLoginBtn)

        mRegisterEmail = findViewById(R.id.RegisterEmail);
        mRegisterPassword = findViewById(R.id.RegisterPassword)

        mAuth = FirebaseAuth.getInstance();
        mProgressbar = ProgressDialog(this)

        mRegisterLoginBtn.setOnClickListener {

            val loginIntent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(loginIntent)

        }



        mRegisterBtn.setOnClickListener {
            val email = mRegisterEmail.text.toString().trim()
            val password = mRegisterPassword.text.toString().trim()


            if (TextUtils.isEmpty(email)) {

                mRegisterEmail.error = "Enter Email"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {

                mRegisterPassword.error = "Enter Password"
                return@setOnClickListener
            }

            createUser(email, password)
        }
    }

         fun createUser( email: String, password: String) {

            mProgressbar.setMessage("Please wait..")
            mProgressbar.show()


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val Intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(Intent)
                            finish()
                            mProgressbar.dismiss()

                        } else {

                            Toast.makeText(this, "Authentication failed.${task.exception}", Toast.LENGTH_SHORT).show()
                            mProgressbar.dismiss()

                        }
                    }
        }
    }



