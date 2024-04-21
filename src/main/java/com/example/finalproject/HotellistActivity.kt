package com.example.finalproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class HotellistActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var hotelArrayList : ArrayList<Hotel>


    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("Hotels")  // Make sure it matches your database structure
        dbref.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                hotelArrayList.clear()  // Clear existing data
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val hotel = userSnapshot.getValue(Hotel::class.java)
                        if (hotel != null) {  // Check for null to avoid crashes
                            hotelArrayList.add(hotel)
                        }
                    }
                    userRecyclerview.adapter?.notifyDataSetChanged()  // Notify the adapter of data change
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Database operation canceled: ${error.message}")
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotellist)

        userRecyclerview = findViewById(R.id.hotelList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        hotelArrayList = arrayListOf<Hotel>()
        userRecyclerview.adapter = MyHotelAdapter(hotelArrayList)  // Set the adapter once here
        getUserData()
    }

}