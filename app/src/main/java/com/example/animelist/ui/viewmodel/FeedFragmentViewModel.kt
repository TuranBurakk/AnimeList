package com.example.animelist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animelist.data.Model
import com.example.animelist.service.AnimeAPIService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class FeedFragmentViewModel @Inject constructor(private val disposable : CompositeDisposable) : ViewModel() {
    private val animeApiService = AnimeAPIService()

    private val _anime : MutableLiveData<List<Model>> = MutableLiveData()
    val anime : LiveData<List<Model>> = _anime


    fun getDataFromAPI() {
        disposable.add(
            animeApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Model>>() {
                    override fun onSuccess(t: List<Model>) {
                        _anime.value = t
                        showAnime(t)
                        println(t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        println("sa")

                    }

                })
        )
    }
    private fun showAnime(animeList: List<Model>){
        _anime.value = animeList
    }

}