package com.example.health_tracker_app.adapters


import HomeFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.health_tracker_app.fragments.AnalizeFragment
import com.example.health_tracker_app.fragments.QuationsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return   when(position){
            0->{
                HomeFragment()
            }
            1->{
                QuationsFragment()
            }
            2->{
                AnalizeFragment()
            }
            else->{
                Fragment()
            }

        }
    }
}