package com.example.finalproject

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyLocationAdapter(private val locationList : ArrayList<Location>) : RecyclerView.Adapter<MyLocationAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.location_item,
            parent,false)
        return MyViewHolder(itemView)

    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = locationList[position]
        holder.location.text = currentItem.location
        holder.lat.text = currentItem.lat.toString()
        holder.lon.text = currentItem.lon.toString()


        holder.itemView.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:${currentItem.lat},${currentItem.lon}?q=${currentItem.lat},${currentItem.lon}(${currentItem.location})")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")  // Specifically targeting Google Maps

            // Check specifically for Google Maps availability
            if (mapIntent.resolveActivity(holder.itemView.context.packageManager) != null) {
                holder.itemView.context.startActivity(mapIntent)
            } else {
                // Google Maps is not installed, open location in Google Maps on web
                Toast.makeText(holder.itemView.context, "Google Maps is not installed, opening in browser.", Toast.LENGTH_LONG).show()
                val webIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${currentItem.lat},${currentItem.lon}")
                val webIntent = Intent(Intent.ACTION_VIEW, webIntentUri)
                if (webIntent.resolveActivity(holder.itemView.context.packageManager) != null) {
                    holder.itemView.context.startActivity(webIntent)
                } else {
                    Toast.makeText(holder.itemView.context, "No web browser available.", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    override fun getItemCount(): Int {

        return locationList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val location : TextView = itemView.findViewById(R.id.tvlocation)
        val lat : TextView = itemView.findViewById(R.id.tvlat)
        val lon : TextView = itemView.findViewById(R.id.tvlon)

    }

}