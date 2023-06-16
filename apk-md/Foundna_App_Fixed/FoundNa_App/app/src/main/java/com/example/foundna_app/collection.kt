package com.example.foundna_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class collection : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var shoplist: ArrayList<Petshop>
    private lateinit var petshopAdapter: PetshopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)

        init()
    }
    private fun init(){
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        shoplist = ArrayList()
        addDataToList()

        petshopAdapter = PetshopAdapter(shoplist)
        recyclerView.adapter = petshopAdapter

    }

    private fun addDataToList(){
        shoplist.add(Petshop(R.drawable.removebgkandang, "Kandang Hewan Sedang"))
        shoplist.add(Petshop(R.drawable.royalcanin, "Royal Canin Makanan Kucing/anjing"))
        shoplist.add(Petshop(R.drawable.akuarium, "Akuarium 20x30"))
        shoplist.add(Petshop(R.drawable.literbocremove, "Liter Box untuk Anjing/Kucing"))
        shoplist.add(Petshop(R.drawable.filterakuarium, "Filter akuarium"))
        shoplist.add(Petshop(R.drawable.serbukkayu, "Serbuk kayu untuk Kura-kura, Hamster, Ular"))

    }
}