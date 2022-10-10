package com.example.animelist.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist.data.Model
import com.example.animelist.service.AnimeDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(private val animeDao: AnimeDao) : ViewModel() {
    val animeLiveData = MutableLiveData<Model>()
    fun getDataFromRoom(id : String){
        viewModelScope.launch {

            val anime = animeDao.getAnime(id)
            animeLiveData.value = anime
        }
    }
}