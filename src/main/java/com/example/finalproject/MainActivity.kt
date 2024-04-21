package com.example.finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    lateinit var cardView: CardView // Change the type to CardView
    lateinit var cardView2: CardView // Change the type to CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardView = findViewById(R.id.cardView) // Change the ID to the ID of your CardView
        cardView2 = findViewById(R.id.cardView2)
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
    }
}