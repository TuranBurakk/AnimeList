package com.example.animelist.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.animelist.base.BaseViewModel
import com.example.animelist.data.Model
import com.example.animelist.service.AnimeDatabase
import kotlinx.coroutines.launch

class DetailsFragmentViewModel(application: Application): BaseViewModel(application) {
    val animeLiveData = MutableLiveData<Model>()
    fun getDataFromRoom(id : String){
        launch {
            val dao = AnimeDatabase(getApplication()).animeDao()
            val anime = dao.getAnime(id)
            animeLiveData.value = anime
        }
    }
}