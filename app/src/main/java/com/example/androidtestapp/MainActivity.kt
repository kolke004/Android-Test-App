package com.example.androidtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar

class MainActivity : AppCompatActivity() {

    private lateinit var actionBar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar = supportActionBar!!
        actionBar.title = "Testing Page"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() //go back to previous activity when back button of actionbar clicked
        return super.onSupportNavigateUp()
    }
}