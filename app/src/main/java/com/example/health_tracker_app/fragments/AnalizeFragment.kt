package com.example.health_tracker_app.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

        stressP.text = "Positive -> $r_stressP"
        stressN.text = "Negative -> $r_stressN"

        alcoholP.text = "Positive -> $r_alcoholP"
        alcoholN.text = "Negative -> $r_alcoholN"

        nutritionP.text = "Positive -> $r_nutritionP"
        nutritionN.text = "Negative -> $r_nutritionN"

        sleepP.text = "Positive -> $r_sleepP"
        sleepN.text = "Negative -> $r_sleepN"



        return view
    }

    companion object {
    }
}