package com.example.newscontroller.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newscontroller.base.BaseViewModel
import com.example.newscontroller.data.NewsBody
import com.example.newscontroller.repository.PoliticsRepository
import kotlinx.coroutines.launch

class PoliticsViewModel(val newsRepository: PoliticsRepository): BaseViewModel() {

    private var searchedPoliticsText = "Trump"

    private val politicsResults: MutableLiveData<NewsBody> = MutableLiveData()

    fun getPolitics():LiveData<NewsBody> = politicsResults

    init {
        loadData()
    }

    fun loadData() {
        launch {
            val response = newsRepository.getPoliticsNews(searchedPoliticsText)
            response.either(::showFailure){ onLoadSuccess(it)}
        }.also { addJob(it) }

    }


    fun onLoadSuccess(nesBody: NewsBody){
        politicsResults.value = nesBody
    }

    fun setSearchedText(search:String){
        searchedPoliticsText = search
    }
}