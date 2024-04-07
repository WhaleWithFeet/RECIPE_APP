package com.kroger.classapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kroger.classapp.ui.fragments.RandomRecipeGenerator
import com.kroger.classapp.ui.fragments.RecipeOfTheDayFragment
import com.kroger.classapp.ui.fragments.RecipeSearch
import com.kroger.classapp.ui.adapter.ViewPagerAdapter
import com.kroger.classapp.ui.fragments.TestFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        val fragments = listOf(RecipeSearch(), RecipeOfTheDayFragment(), RandomRecipeGenerator())
        val adapter = ViewPagerAdapter(fragments, supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        val tabLayout: TabLayout = findViewById(R.id.recipe_tab)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if(position == 0){
                tab.text = "RECIPE OF THE DAY"
            }
            if(position == 1){
                tab.text = "RECIPE SEARCH"
            }
            if(position == 2){
                tab.text = "RANDOM RECIPE GENERATOR"
            }
        }.attach()

    }

}
