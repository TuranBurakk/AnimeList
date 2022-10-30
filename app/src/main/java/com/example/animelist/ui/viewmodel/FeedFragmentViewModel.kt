package com.example.animelist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist.data.AnimeData
import com.example.animelist.data.AnimeModel
import com.example.animelist.data.Repository
import com.example.animelist.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedFragmentViewModel @Inject constructor(
    private val repository: Repository) : ViewModel(){


    fun getDataFromAPI(): LiveData<Resource<List<AnimeModel>>> {
     return repository.getAnimes()
    }
    fun getFavorites() = repository.getFav()

    fun addToFavorites(animes : AnimeModel){
        viewModelScope.launch { repository.addToFavorites(animes) }
    }

    fun deleteFromFavorites(animes : AnimeModel){
        viewModelScope.launch { repository.deleteFromFavorites(animes) }
    }
}