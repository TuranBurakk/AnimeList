package com.example.animelist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist.data.Model
import com.example.animelist.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavFragmentViewModel @Inject constructor(
private val repository: Repository): ViewModel() {

    fun getFav() = repository.getFav()

    fun deleteFromFav(model: Model){
        viewModelScope.launch(IO){ repository.deleteFromFavorites(model) }
    }

}