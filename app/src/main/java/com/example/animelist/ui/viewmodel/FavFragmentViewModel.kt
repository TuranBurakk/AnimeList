package com.example.animelist.ui.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.animelist.base.BaseViewModel
import com.example.animelist.data.Model
import com.example.animelist.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavFragmentViewModel @Inject constructor(application: Application,
private val repository: Repository): BaseViewModel(application) {

    fun getFav() = repository.getFav()

    fun deleteFromFav(model: Model){
        viewModelScope.launch(IO){ repository.deleteFromFavorites(model) }
    }

}