package com.example.animelist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animelist.data.Model
import com.example.animelist.data.Repository
import com.example.animelist.service.AnimeDao
import com.example.animelist.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val animeLiveData = MutableLiveData<Model>()
    fun getDataFromRoom(id : String) : LiveData<Resource<Model>>{
        return this.repository.getdetails(id)
    }
}