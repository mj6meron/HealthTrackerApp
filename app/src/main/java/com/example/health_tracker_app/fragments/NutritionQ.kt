package com.example.health_tracker_app.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.health_tracker_app.R


class NutritionQ : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_with_quation, container, false)

        val question1 = view.findViewById<TextView>(R.id.question_1_label)
        val answerField = view.findViewById<EditText>(R.id.question_1)
        val saveButton = view.findViewById<Button>(R.id.save_answer)

        question1.text = "Did you have some servings of fruits and vegetables today?"
        saveButton.setOnClickListener {
            val sharedPref = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("nutrition_question", answerField.text.toString())
            editor.apply()
        }
        return view
    }
}
