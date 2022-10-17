package com.example.animelist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.animelist.data.Model
import com.example.animelist.data.Repository
import com.example.animelist.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FeedFragmentViewModel @Inject constructor(
    private val repository: Repository) : ViewModel(){

    fun getDataFromAPI(): LiveData<Resource<Model>> {
     return repository.getAnimes()
    }
    private fun showAnime(animeList: Response<Model>){

    }

}