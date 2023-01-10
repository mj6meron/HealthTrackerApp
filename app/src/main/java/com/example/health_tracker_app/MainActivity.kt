package com.example.health_tracker_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonQ = findViewById<Button>(R.id.buttonQ)

        buttonQ.setOnClickListener{
            val intent = Intent(this, Quations::class.java)
            startActivity(intent)
        }
    }
}