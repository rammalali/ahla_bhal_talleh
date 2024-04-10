package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class FoodlistActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var foodArrayList : ArrayList<Food>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foodlist)

        userRecyclerview = findViewById(R.id.foodList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        foodArrayList = arrayListOf<Food>()
        getUserData()

    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("Food")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val food = userSnapshot.getValue(Food::class.java)
                        foodArrayList.add(food!!)

                    }

                    userRecyclerview.adapter = MyAdapter(foodArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Database operation canceled: ${error.message}")
            }


        })

    }
}