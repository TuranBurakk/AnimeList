package com.example.animelist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animelist.data.AnimeModel
import com.example.animelist.data.Repository
import com.example.animelist.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val animeLiveData = MutableLiveData<AnimeModel>()
    fun getDataFromRoom(id : String) : LiveData<Resource<AnimeModel>>{
        return this.repository.getdetails(id)
    }
}