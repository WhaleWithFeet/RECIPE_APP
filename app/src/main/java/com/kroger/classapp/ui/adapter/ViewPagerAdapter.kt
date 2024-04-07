package com.kroger.classapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle

import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(
    private val fragments: List<Fragment>,
    fragmentManager: FragmentManager,
    Lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, Lifecycle) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
