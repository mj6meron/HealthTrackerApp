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
import android.widget.EditText
import android.widget.TextView
import android.os.Handler
import android.widget.Toast
import com.example.health_tracker_app.MainActivity
import org.tensorflow.lite.support.label.Category
import com.example.health_tracker_app.R
import com.example.health_tracker_app.TextClassificationHelper


class SleepQ : Fragment() {
    private lateinit var classifierHelper: TextClassificationHelper

    private val listOfClassifiedResults = mutableListOf<ClassificationResults>()
    private var inputText: String = ""
    private var model = "MobileBert"
    private var delegate = 0

    var resultsss = ""


    private val listener = object : TextClassificationHelper.TextResultsListener {
        @SuppressLint("NotifyDataSetChanged")
        override fun onResult(results: List<Category>, inferenceTime: Long) {
            val sharedPref = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            Handler(requireContext().mainLooper).post {
                val resultsList = results.sortedByDescending { it.score }
                val resultString = StringBuilder()
                for (result in resultsList) {
                    val label = result.label
                    val score = result.score
                    editor.putFloat("sleep_$label", score)
                    editor.apply()
                    //val resultObj = ClassificationResults(label, score)
                    //listOfClassifiedResults.add(resultObj)
                    resultString.append("This is $label with score of $score \n")
                }
                resultsss = resultString.toString()
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
        val classfiedView = view.findViewById<TextView>(R.id.classified_ans)
        val answerField = view.findViewById<EditText>(R.id.question_1)
        val saveButton = view.findViewById<Button>(R.id.save_answer)

        question1.text = "Did you have trouble falling asleep or staying asleep last night?"

        // Create the classification helper that will do the heavy lifting
        classifierHelper = TextClassificationHelper(
            context = requireContext(),
            listener = listener
        )

        saveButton.setOnClickListener {
            val sharedPref = requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            inputText = answerField.text.toString()
            classifierHelper.currentDelegate = delegate
            classifierHelper.currentModel = TextClassificationHelper.MOBILEBERT
            classifierHelper.initClassifier()
            if (inputText.isEmpty()) {
                Toast.makeText(
                    view.context,
                    "Please say something for a result",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                classifierHelper.classify(inputText)
                editor.putString("sleep_question", inputText)
                editor.apply()
                classfiedView.text = resultsss
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.putExtra("SELECT_QUATIONS_TAB", true)
                startActivity(intent)
            }
        }

        return view
    }
}

//         question1.text = "How often do you drink alcohol in a week?"
//question1.text = "Did you have some servings of fruits and vegetables today?"
// question1.text = "Did you have trouble falling asleep or staying asleep last night?"