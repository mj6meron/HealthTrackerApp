package com.example.health_tracker_app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.health_tracker_app.MainActivity
import com.example.health_tracker_app.R
import com.example.health_tracker_app.RowsQuationsTab
import com.example.health_tracker_app.adapters.RowAdopter

class QuationsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_quations, container, false)


        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewQuations)

        val listRows = ArrayList<RowsQuationsTab>()
        listRows.add(RowsQuationsTab("Sleep", R.drawable.sleepicon))
        listRows.add(RowsQuationsTab("Nutrition", R.drawable.nutritionicon))
        listRows.add(RowsQuationsTab("Stress", R.drawable.stressicon))
        listRows.add(RowsQuationsTab("Alcohol", R.drawable.alcoholicon))
        recyclerView.adapter = RowAdopter(listRows, this)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view


    }
    fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                val intent = Intent(this@QuationsFragment.requireActivity(), MainActivity::class.java)
                requireActivity().startActivity(intent)
            }
            1 -> {
                val intent = Intent(this@QuationsFragment.requireActivity(), MainActivity::class.java)
                requireActivity().startActivity(intent)
            }
            2 -> {
                Toast.makeText(requireContext(),"You clicked the stress button", Toast.LENGTH_SHORT).show()
            }
            3 -> {
                Toast.makeText(requireContext(),"You clicked the alcohol button", Toast.LENGTH_SHORT).show()
            }

        }
    }


}