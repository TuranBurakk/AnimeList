package com.example.animelist.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.animelist.base.BaseFragment
import com.example.animelist.databinding.FragmentFeedBinding
import com.example.animelist.ui.adapter.FeedAdapter
import com.example.animelist.ui.viewmodel.FeedFragmentViewModel


class FeedFragment : BaseFragment<FragmentFeedBinding>(FragmentFeedBinding::inflate) {

    private val viewModel by viewModels<FeedFragmentViewModel>()
    private val adapter by lazy { FeedAdapter(arrayListOf()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDataFromAPI()
        observeLiveData()
        binding.animeRecyclerView.adapter = adapter


    }
    fun observeLiveData(){
        viewModel.anime.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.updateAnimeList(it)
            }
        })
    }
}