package com.example.health_tracker_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.health_tracker_app.R
import com.example.health_tracker_app.RowsQuationsTab
import com.example.health_tracker_app.fragments.QuationsFragment

class RowAdopter(
    private val data: List<RowsQuationsTab>,
    private val listener: QuationsFragment
) : RecyclerView.Adapter<RowAdopter.ItemViewHolder>()  {

    //Setup variables to hold the instance of the views defined in your recyclerView item layout
    //Kinda like the onCreate method in an Activity
    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val rowName: TextView = view.findViewById(R.id.rowName)
        val rowImage: ImageView = view.findViewById(R.id.rowImage)
        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    //This is where you inflate the layout (Give each entry/row its look)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_row, parent, false)
        return ItemViewHolder(inflatedView)
    }

    // Set values to the views we pulled out of the recycler_view_row
    // layout file based on the position of the recyclerView
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val rows: RowsQuationsTab = data[position]

        holder.rowName.text = rows.name
        holder.rowImage.setImageResource(rows.picture)
    }

    //The recyclerView just wants to know how many items are currently in your dataset
    override fun getItemCount(): Int {
        return data.size
    }

// click listner interface for each row
    interface RecyclerViewEvent{
        fun onItemClick(position: Int)
    }
}