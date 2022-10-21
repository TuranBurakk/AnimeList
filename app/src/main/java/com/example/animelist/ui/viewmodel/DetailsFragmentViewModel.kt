package com.example.animelist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist.data.AnimeModel
import com.example.animelist.data.Repository
import com.example.animelist.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    fun getDataFromRoom(id : String) : LiveData<Resource<AnimeModel>>{
        return repository.getdetails(id)
    }
    fun getFavorites() = repository.getFav()

    fun addToFavorites(animes: AnimeModel) {
        viewModelScope.launch(Dispatchers.IO) { repository.addToFavorites(animes) }
    }

    fun deleteFromFavorites(animes: AnimeModel) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteFromFavorites(animes) }
    }
}