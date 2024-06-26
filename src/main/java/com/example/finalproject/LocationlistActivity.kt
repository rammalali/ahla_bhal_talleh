package com.example.finalproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class LocationlistActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var locationArrayList : ArrayList<Location>


    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("Locations")  // Make sure it matches your database structure
        dbref.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                locationArrayList.clear()  // Clear existing data
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val location = userSnapshot.getValue(Location::class.java)
                        if (location != null) {  // Check for null to avoid crashes
                            locationArrayList.add(location)
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
        setContentView(R.layout.activity_locationlist)

        userRecyclerview = findViewById(R.id.locationList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        locationArrayList = arrayListOf<Location>()
        userRecyclerview.adapter = MyLocationAdapter(locationArrayList)  // Set the adapter once here
        getUserData()
    }

}