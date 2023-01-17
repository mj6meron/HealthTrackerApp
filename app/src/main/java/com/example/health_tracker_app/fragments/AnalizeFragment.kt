package com.example.health_tracker_app.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.health_tracker_app.BarChartActivity
import com.example.health_tracker_app.MainActivity
import com.example.health_tracker_app.R

class AnalizeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_analize, container, false)

        val sharedPref = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val r_sleepP = sharedPref.getFloat("sleep_positive", 0.0F)
        val r_sleepN = sharedPref.getFloat("sleep_negative", 0.0F)
        val r_nutritionP = sharedPref.getFloat("nutrition_positive", 0.0F)
        val r_nutritionN = sharedPref.getFloat("nutrition_negative", 0.0F)
        val r_stressP = sharedPref.getFloat("stress_positive", 0.0F)
        val r_stressN = sharedPref.getFloat("stress_negative", 0.0F)
        val r_alcoholP = sharedPref.getFloat("alcohol_positive", 0.0F)
        val r_alcoholN = sharedPref.getFloat("alcohol_negative", 0.0F)

        val sleepP = view.findViewById<TextView>(R.id.sleep_positive)
        val sleepN = view.findViewById<TextView>(R.id.sleep_negative)
        val nutritionP = view.findViewById<TextView>(R.id.nutrition_positive)
        val nutritionN = view.findViewById<TextView>(R.id.nutrition_negative)
        val stressP = view.findViewById<TextView>(R.id.stress_positive)
        val stressN = view.findViewById<TextView>(R.id.stress_negative)
        val alcoholP = view.findViewById<TextView>(R.id.alcohol_positive)
        val alcoholN = view.findViewById<TextView>(R.id.alcohol_negative)
        val statBtn = view.findViewById<Button>(R.id.statBtn)




        val sleep = view.findViewById<TextView>(R.id.TitleCondition1)
        val nutrition = view.findViewById<TextView>(R.id.TitleCondition2)
        val stress = view.findViewById<TextView>(R.id.TitleCondition3)
        val alcohol = view.findViewById<TextView>(R.id.TitleCondition4)

        sleep.text = "Sleep" + compare(r_sleepP, r_sleepN)
        nutrition.text = "Nutrition" + compare(r_nutritionP, r_nutritionN)
        stress.text = "Stress" + compare(r_stressP, r_stressN)
        alcohol.text = "Alcohol" + compare(r_alcoholP, r_alcoholN)


        stressP.text = "Positive -> $r_stressP"
        stressN.text = "Negative -> $r_stressN"

        alcoholP.text = "Positive -> $r_alcoholP"
        alcoholN.text = "Negative -> $r_alcoholN"

        nutritionP.text = "Positive -> $r_nutritionP"
        nutritionN.text = "Negative -> $r_nutritionN"

        sleepP.text = "Positive -> $r_sleepP"
        sleepN.text = "Negative -> $r_sleepN"


        statBtn.setOnClickListener {
            val intent = Intent(requireContext(), BarChartActivity::class.java)
            startActivity(intent)
        }


        return view
    }

    private fun compare(p : Float, n: Float) : String {
        return if (p > n) {
            " is good"
        } else if (n > p){
            " is not good"
        } else {
            ""
        }
    }
}