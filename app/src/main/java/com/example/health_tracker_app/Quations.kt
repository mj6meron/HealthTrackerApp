package com.example.health_tracker_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Quations : AppCompatActivity(), RowAdopter.RecyclerViewEvent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quations)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewQuations)

        val listRows = ArrayList<RowsQuationsTab>()
        listRows.add(RowsQuationsTab("Sleep", R.drawable.sleepicon))
        listRows.add(RowsQuationsTab("Nutrition", R.drawable.nutritionicon))
        listRows.add(RowsQuationsTab("Stress", R.drawable.stressicon))
        listRows.add(RowsQuationsTab("Alcohol", R.drawable.alcoholicon))

        recyclerView.adapter = RowAdopter(listRows, this)
        recyclerView.layoutManager = LinearLayoutManager(this)

        Toast.makeText(this,"hi there", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                val intent = Intent(this@Quations, MainActivity::class.java)
                startActivity(intent)
            }
            1 -> {
                val intent = Intent(this@Quations, Quations::class.java)
                startActivity(intent)
            }
            2 -> {
                Toast.makeText(this,"hi there", Toast.LENGTH_SHORT).show()
            }
            3 -> {
                Toast.makeText(this,"hi there", Toast.LENGTH_SHORT).show()
            }
            
        }
    }
}