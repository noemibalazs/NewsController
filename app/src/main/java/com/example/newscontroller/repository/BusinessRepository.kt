package com.example.newscontroller.repository

import com.example.newscontroller.core.Either
import com.example.newscontroller.core.Failure
import com.example.newscontroller.core.NewsSource
import com.example.newscontroller.data.NewsBody
import com.example.newscontroller.utils.KEY
import com.example.newscontroller.utils.ORDER_BY_BUSINESS

class BusinessRepository(val newsSource: NewsSource) {

    suspend fun getBusiness(search: String): Either<Failure, NewsBody>{
        return newsSource.getBusinessNews(KEY, search, ORDER_BY_BUSINESS )
    }
}