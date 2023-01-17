package com.example.health_tracker_app

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm



class BarChartActivity : AppCompatActivity() {

    lateinit var barChart:BarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart)


        barChart=findViewById(R.id.bar_chart)


        val list: ArrayList<BarEntry> = ArrayList()


        val sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE)

        val r_sleepP = sharedPref.getFloat("sleep_positive", 0.0F)
        val r_sleepN = sharedPref.getFloat("sleep_negative", 0.0F)
        val r_nutritionP = sharedPref.getFloat("nutrition_positive", 0.0F)
        val r_nutritionN = sharedPref.getFloat("nutrition_negative", 0.0F)
        val r_stressP = sharedPref.getFloat("stress_positive", 0.0F)
        val r_stressN = sharedPref.getFloat("stress_negative", 0.0F)
        val r_alcoholP = sharedPref.getFloat("alcohol_positive", 0.0F)
        val r_alcoholN = sharedPref.getFloat("alcohol_negative", 0.0F)

        val barEntries: ArrayList<BarEntry> = ArrayList()
        barEntries.add(BarEntry(0f, floatArrayOf(r_sleepP, r_sleepN)))
        barEntries.add(BarEntry(1f, floatArrayOf(r_nutritionP, r_nutritionN)))
        barEntries.add(BarEntry(2f, floatArrayOf(r_stressP, r_stressN)))
        barEntries.add(BarEntry(3f, floatArrayOf(r_alcoholP, r_alcoholN)))

        val barDataSet = BarDataSet(barEntries, "")
        barDataSet.setColors(*intArrayOf(Color.GREEN, Color.RED))
        barDataSet.stackLabels = arrayOf("Positive", "Negative")

        barDataSet.valueTextColor = Color.BLACK

        val barData = BarData(barDataSet)
        barChart.data = barData
        barChart.description.text = "Bar Chart"
        barChart.animateY(2000)

        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(listOf("Sleep","Nutrition","Stress","Alcohol"))
        barChart.axisLeft.valueFormatter = IndexAxisValueFormatter(listOf("0","20","40","60","80","100"))

        barChart.xAxis.axisMinimum = -0.5f
        barChart.xAxis.axisMaximum = 3.5f
        barChart.barData.barWidth = 0.9f

        barChart.data = barData
        barChart.setDrawValueAboveBar(false)
        barChart.getAxisRight().setDrawLabels(false)
        barChart.getAxisLeft().setDrawLabels(true)
        barChart.legend.isEnabled = true
        barChart.legend.textSize = 12f


        barChart.xAxis.textSize = 12f
        barChart.axisLeft.textSize = 12f

        barChart.legend.isEnabled = true

        barChart.legend.setForm(LegendForm.SQUARE)
        barChart.legend.setFormSize(10f)

    }
}