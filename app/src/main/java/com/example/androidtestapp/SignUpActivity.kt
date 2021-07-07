package com.example.androidtestapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtestapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpActivity : AppCompatActivity() {
    //ViewBinding
    private lateinit var binding: ActivitySignUpBinding

    //ActionBar
    private lateinit var actionBar: ActionBar

    //ProgressDialog
    private lateinit var progressDialog: ProgressDialog

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure action bar, enable back button
        actionBar = supportActionBar!!
        actionBar.title = "Sign Up"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        //configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Creating Account In...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        //handle click, begin signup
        binding.signUpBtn.setOnClickListener {
            //validate data
            validateData()
        }
    }

    private fun validateData() {
        //get data
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        //validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format
            binding.emailEt.error = "Invalid Email Format"
        }
        else if (TextUtils.isEmpty(password)){
            //password isn't entered
            binding.passwordEt.error = "Please Enter Password"
        }
        else if (password.length <6){
            //passowrd length is less than 6
            binding.passwordEt.error = "Password Must Be At Least 6 Characters Long"
        }
        else{
            //data is valid, continue signup
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        //show progress
        progressDialog.show()

        //create account
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //signup success
                progressDialog.dismiss()
                //get current user
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Account Created With Email $email", Toast.LENGTH_SHORT).show()

                //open profile
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()

            }
            .addOnFailureListener {e->
                //signup failed
                progressDialog.dismiss()
                Toast.makeText(this, "SignUp Failed Due To ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() //go back to previous activity when back button of actionbar clicked
        return super.onSupportNavigateUp()
    }
}