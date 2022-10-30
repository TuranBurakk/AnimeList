package com.example.animelist.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.animelist.base.BaseFragment
import com.example.animelist.data.AnimeModel
import com.example.animelist.databinding.FragmentFeedBinding
import com.example.animelist.ui.adapter.FeedAdapter
import com.example.animelist.ui.viewmodel.FeedFragmentViewModel
import com.example.animelist.utils.Resource
import com.example.animelist.utils.showDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : BaseFragment<FragmentFeedBinding>(FragmentFeedBinding::inflate) , FavItem {

    private val viewModel : FeedFragmentViewModel by viewModels()
    private val adapter by lazy { FeedAdapter(this) }
    private val animeList = arrayListOf<AnimeModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.animeRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        animeList.clear()
        getAnimes()
    }

    private fun getAnimes(){
        viewModel.getDataFromAPI().observe(viewLifecycleOwner){response ->
            when(response.status){
                Resource.Status.SUCCESS ->{
                    response.data?.let { animeList.addAll(it) }
                    checkFavorite()
                }
                Resource.Status.ERROR ->{
                    showDialog(requireContext(), message = "${response.message}")  }
            }
        }
    }

    override fun favItem(item: AnimeModel, position: Int) {
        if(item.isFavorite){
            viewModel.addToFavorites(item)
        }else{
            viewModel.deleteFromFavorites(item)
        }
    }
    private fun checkFavorite() {
        val localFavoriteList = viewModel.getFavorites()
        for (item in localFavoriteList) {
            animeList.find {
                it.id == item.id
            }?.isFavorite = true
        }
        setData()
    }
    private fun setData() {
        adapter.updateAnimeList(animeList)
    }


}