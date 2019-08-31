package com.example.newscontroller.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newscontroller.base.BaseViewModel
import com.example.newscontroller.data.NewsBody
import com.example.newscontroller.repository.SportsRepository
import kotlinx.coroutines.launch

class SportsViewModel (val sportsRepository: SportsRepository) : BaseViewModel() {

    private var sportSearch = "Jordan"

    private var results: MutableLiveData<NewsBody> = MutableLiveData()

    fun getResults():LiveData<NewsBody> = results


    init {
        loadNewsData()
    }

    fun loadNewsData(){
        launch {
            val response = sportsRepository.getSportsNews(sportSearch)
            response.either(::showFailure){ onLoadSuccess(it)}
        }.also { addJob(it) }
    }

    fun onLoadSuccess(newsBody: NewsBody){
        results.value = newsBody

    }

    fun setSearch(search: String){
        sportSearch = search
    }


}