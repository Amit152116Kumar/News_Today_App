package com.example.news.news_categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.news.FragmentViewModel
import com.example.news.adapters.RecyclerViewAdapter
import com.example.news.databinding.FragmentViewBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class Business : Fragment() {

    private val category = "business"
    private lateinit var viewmodel: FragmentViewModel
    private var _binding: FragmentViewBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(requireActivity()).get(FragmentViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentViewBinding.inflate(inflater, container, false)
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


        binding.refresh.setOnRefreshListener {

            MainScope().launch {
                viewmodel.topHeadlines(category).collect {
                    mAdapter.updateNews(it)
                }
            }

            binding.refresh.isRefreshing = false
        }
    }


}