package com.example.newscontroller.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newscontroller.base.BaseViewModel
import com.example.newscontroller.data.NewsBody
import com.example.newscontroller.repository.BusinessRepository
import kotlinx.coroutines.launch

class BusinessViewModel(val businessRepository: BusinessRepository): BaseViewModel() {

    private var search: String = "euro"

    private val business: MutableLiveData<NewsBody> = MutableLiveData()

    fun getBusinessData(): LiveData<NewsBody> = business

    init {
        loadBusinessData()
    }

    fun  loadBusinessData(){
        launch {
            val response = businessRepository.getBusiness(search)
            response.either(::showFailure){ onLoadSuccess(it)}
        }.also { addJob(it) }
    }

    fun onLoadSuccess(news: NewsBody){
        business.value = news
    }

    fun setSearchedText(text: String){
        search = text
    }
}