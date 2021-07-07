package com.example.androidtestapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtestapp.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import sdk.pendo.io.Pendo.PendoInitParams


class ProfileActivity : AppCompatActivity() {
    //ViewBinding
    private lateinit var binding: ActivityProfileBinding

    //ActionBar
    private lateinit var actionBar: ActionBar

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//PENDO METADATA//
        //send Firebase visitor/accountIds to Pendo
        val visitorId = FirebaseAuth.getInstance().currentUser!!.uid
        val accountId = "TestMobile"
// DO NOT UNCOMMENT
//        val email = FirebaseAuth.getInstance().currentUser!!.email

        val pendoParams = PendoInitParams()
        pendoParams.visitorId = visitorId
        pendoParams.accountId = accountId


        val visitorData: MutableMap<String, Any> = HashMap()
// DO NOT UNCOMMENT
//        visitorData["email"] = email
        visitorData["country"] = "USA"
        pendoParams.visitorData = visitorData
//PENDO METADATA//

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure ActionBar
        actionBar = supportActionBar!!
        actionBar.title = "Profile"

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handle click, logout
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

        binding.mainActivityBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun checkUser() {
        //check user is logged in or not
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            //user not null, user logged in, get user info
            val email = firebaseUser.email
            //set to text view
            binding.emailTv.text = email
        }
        else{
            //user is null is not logged in, go to login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}