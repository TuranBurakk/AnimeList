package com.example.animelist.ui.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animelist.base.BaseViewModel
import com.example.animelist.data.Model
import com.example.animelist.service.AnimeAPIService
import com.example.animelist.service.AnimeDatabase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.launch

class FeedFragmentViewModel(application: Application): BaseViewModel(application) {
    private val disposable = CompositeDisposable()
    private val animeApiService = AnimeAPIService()

    private val _anime : MutableLiveData<List<Model>> = MutableLiveData()
    val anime : LiveData<List<Model>> = _anime

    private fun getDataFromSQLite(){
        launch {
            val anime = AnimeDatabase(getApplication()).animeDao().getAllAnime()
            showAnime(anime)
            Toast.makeText(getApplication(),"Countries From SQLite",Toast.LENGTH_LONG).show()
        }
    }
    fun getDataFromAPI() {
        disposable.add(
            animeApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Model>>() {
                    override fun onSuccess(t: List<Model>) {
                        _anime.value = t
                        storeInSQLite(t)
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
    private fun storeInSQLite(list: List<Model>) {
        launch {
            val dao = AnimeDatabase(getApplication()).animeDao()
            dao.deleteAllAnime()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size){
                list[i].uuid = listLong[i].toInt()
                i += 1
            }
            showAnime(list)
        }
    }
}