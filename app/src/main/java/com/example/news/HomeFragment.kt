package com.example.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.news.adapters.FragmentPagerAdapter
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.news_categories.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val fragmentList = listOf(
        Business(), Entertainment(), General(), Health(),
        Science(), Sports(), Technology()
    )
    private val fragmentTitle =
        listOf("Business", "Entertainment", "General", "Health", "Science", "Sports", "Technology")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentAdapter = FragmentPagerAdapter(childFragmentManager, lifecycle, fragmentList)
        binding.viewPager.adapter = fragmentAdapter

        // TabLayout Mediator
        TabLayoutMediator(binding.tablayout, binding.viewPager) { tab: TabLayout.Tab, i: Int ->
            tab.text = fragmentTitle[i]
        }.attach()


    }
}