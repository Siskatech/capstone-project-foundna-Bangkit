package com.example.foundna_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PetshopAdapter (private val shopList: ArrayList<Petshop>) :
    RecyclerView.Adapter<PetshopAdapter.PetshopViewHolder>() {

    class PetshopViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView= itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetshopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return PetshopViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: PetshopViewHolder, position: Int) {
        val petshop = shopList[position]
        holder.imageView.setImageResource(petshop.image)
        holder.textView.text = petshop.name
    }

}