package com.example.health_tracker_app.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.health_tracker_app.R

class SleepQ : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sleep_q, container, false)

        val question1 = view.findViewById<EditText>(R.id.sleep_question_1)
        val saveButton = view.findViewById<Button>(R.id.save_sleep_answers)

        saveButton.setOnClickListener {
            val sharedPref = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            editor.putString("sleep_question", question1.text.toString())
            editor.apply()
        }

        return view
    }
}
