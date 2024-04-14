package com.example.finalproject

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locationlist)

        userRecyclerview = findViewById(R.id.locationList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        locationArrayList = arrayListOf<Location>()
        getUserData()
    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("Location")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val location = userSnapshot.getValue(Location::class.java)
                        locationArrayList.add(location!!)
                    }
                    userRecyclerview.adapter = MyAdapter(locationArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Database operation canceled: ${error.message}")
            }


        })

    }
}