package com.example.health_tracker_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.health_tracker_app.fragments.AlcoholQ
import com.example.health_tracker_app.fragments.NutritionQ
import com.example.health_tracker_app.fragments.SleepQ
import com.example.health_tracker_app.fragments.StressQ

class QuationsToAsk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quations_to_ask)



        val sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()



        val questionType = sharedPref.getString("questionType", "")

        if (questionType == "sleep") {
            val sleepFragment = SleepQ()
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.flQuation, sleepFragment)
                commit()
            }
        } else if (questionType == "nutrition") {

            val nutritionFragment = NutritionQ()
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.flQuation, nutritionFragment)
                commit()
            }
        } else if (questionType == "stress") {
            val stressFragment = StressQ()
            supportFragmentManager.beginTransaction()
                .replace(R.id.flQuation, stressFragment)
                .commit()
        }else if (questionType == "alcohol") {
            val alcoholFragment = AlcoholQ()
            supportFragmentManager.beginTransaction()
                .replace(R.id.flQuation, alcoholFragment)
                .commit()
        }

    }
}