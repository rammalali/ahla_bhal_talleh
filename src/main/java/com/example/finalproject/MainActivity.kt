package com.example.finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Button
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var cardView: CardView // Change the type to CardView
    lateinit var cardView2: CardView // Change the type to CardView
    lateinit var cardView3: CardView // Change the type to CardView

    private lateinit var logoutButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logoutButton = findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        cardView = findViewById(R.id.cardView) // Change the ID to the ID of your CardView
        cardView2 = findViewById(R.id.cardView2)
        cardView3 = findViewById(R.id.cardView3)

        cardView.setOnClickListener { // Update the listener accordingly
            var i = Intent(this, FoodlistActivity::class.java)
            startActivity(i)
            finish()
        }

        cardView2.setOnClickListener { // Update the listener accordingly
            var i = Intent(this, LocationlistActivity::class.java)
            startActivity(i)
            finish()
        }

        cardView3.setOnClickListener {
            var i = Intent(this, HotellistActivity::class.java)
            startActivity(i)
            finish()
        }

    }


}