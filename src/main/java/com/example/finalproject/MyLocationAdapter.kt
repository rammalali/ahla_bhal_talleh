package com.example.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyLocationAdapter(private val locationList : ArrayList<Location>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.location_item,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = locationList[position]

        holder.location.text = currentitem.location
        holder.lat.text = currentitem.lat
        holder.time.text = currentitem.time

    }

    override fun getItemCount(): Int {

        return locationList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val location : TextView = itemView.findViewById(R.id.tvlocation)
        val ingrediant : TextView = itemView.findViewById(R.id.tvingrediant)
        val time : TextView = itemView.findViewById(R.id.tvtime)

    }

}