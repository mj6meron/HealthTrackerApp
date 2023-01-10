package com.example.health_tracker_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.health_tracker_app.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        setContentView(R.layout.activity_main)
        val tabLayout=findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2=findViewById<ViewPager2>(R.id.view_pager_2)

        val adapter= ViewPagerAdapter(supportFragmentManager,lifecycle)
        viewPager2.adapter=adapter

        TabLayoutMediator(tabLayout,viewPager2){tab,position->
            when(position){
                0->{
                    tab.text="Home"
                }
                1->{
                    tab.text="Questions"
                }
                2->{
                    tab.text="Analyzation"
                }
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })


    }
}


/***
 *
 *
 *
 *
 *
editor.apply{
    putString("orderType", "image")
    putInt("orderNumber", 0)
    putString("text", "")
    putString("font", "")
    putString("textSize", "")
    putString("textAlignment", "")
    putString("image", theImage)
    apply()
}



val orderType = sharedPref.getString("orderType", "")

 */