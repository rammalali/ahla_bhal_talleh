package com.example.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val foodList : ArrayList<Food>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.food_item,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = foodList[position]

        holder.food.text = currentitem.food
        holder.ingrediant.text = currentitem.ingrediant
        holder.time.text = currentitem.time

    }

    override fun getItemCount(): Int {

        return foodList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val food : TextView = itemView.findViewById(R.id.tvfood)
        val ingrediant : TextView = itemView.findViewById(R.id.tvingrediant)
        val time : TextView = itemView.findViewById(R.id.tvtime)

    }

}