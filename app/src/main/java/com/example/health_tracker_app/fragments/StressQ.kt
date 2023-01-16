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
import android.os.Handler
import android.widget.Toast
import org.tensorflow.lite.support.label.Category
import com.example.health_tracker_app.R
import com.example.health_tracker_app.TextClassificationHelper

class StressQ : Fragment() {
    private lateinit var classifierHelper: TextClassificationHelper
    private var inputText: String = ""
    private var model = "MobileBert"
    private var delegate = 0
    private val listener = object : TextClassificationHelper.TextResultsListener {
        @SuppressLint("NotifyDataSetChanged")
        override fun onResult(results: List<Category>, inferenceTime: Long) {
            Handler(requireContext().mainLooper).post {
                val resultsList = results.sortedByDescending { it.score }
                for (result in resultsList) {
                    val label = result.label
                    val score = result.score
                    Toast.makeText(view?.context, "This is $label with score of $score", Toast.LENGTH_SHORT).show()
                }
            }
        }
        override fun onError(error: String) {
            Toast.makeText(view?.context, error, Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_with_quation, container, false)

        val question1 = view.findViewById<TextView>(R.id.question_1_label)
        val answerField = view.findViewById<EditText>(R.id.question_1)
        val saveButton = view.findViewById<Button>(R.id.save_answer)

        question1.text = "Did you have any physical symptoms of stress (such as headaches or muscle tension)?"

        // Create the classification helper that will do the heavy lifting
        classifierHelper = TextClassificationHelper(
            context = requireContext(),
            listener = listener
        )

                saveButton.setOnClickListener {
                    inputText = answerField.text.toString()
                    classifierHelper.currentDelegate = delegate

                    when (model) {
                        "MobileBert" -> {
                            classifierHelper.currentModel = TextClassificationHelper.WORD_VEC
                        }
                        "wordVector" -> {
                            classifierHelper.currentModel = TextClassificationHelper.MOBILEBERT
                        }
                    }
                    classifierHelper.initClassifier()
                    if (inputText.isEmpty()) {
                        classifierHelper.classify("This is a default text unfortunately")

                        Toast.makeText(view.context, "This is a default text unfortunately", Toast.LENGTH_SHORT).show()

                    } else {
                        classifierHelper.classify(inputText)
                    }
                    val sharedPref = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("stress_question", inputText)
                    editor.apply()
                }

        return view
    }
}