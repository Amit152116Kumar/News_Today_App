package com.example.news.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.news.FragmentViewModel
import com.example.news.adapters.RecyclerViewAdapter
import com.example.news.databinding.FragmentViewBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect


class BlankFragment : Fragment() {

    private val category = "business"
    private lateinit var viewmodel:FragmentViewModel
    private lateinit var binding: FragmentViewBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel= ViewModelProvider(requireActivity()).get(FragmentViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentViewBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mAdapter = RecyclerViewAdapter()
        binding.recyclerview.adapter = mAdapter


        MainScope().launch {
            viewmodel.topHeadlines(category).collect {
                mAdapter.updateNews(it)
            }
        }


//        binding.refresh.setOnRefreshListener {
//
//            binding.refresh.isRefreshing = false
//        }
    }


}