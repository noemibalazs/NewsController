package com.example.newscontroller.core

import com.example.newscontroller.data.NewsBody
import com.example.newscontroller.extension.awaitCall
import com.example.newscontroller.network.NewsService
import com.example.newscontroller.utils.KEY
import com.example.newscontroller.utils.ORDER_BY_BUSINESS
import com.example.newscontroller.utils.ORDER_BY_POLITICS
import com.example.newscontroller.utils.ORDER_BY_SPORT

class NewsSourceImpl (val newsService: NewsService) : NewsSource {

    override suspend fun getBusinessNews(key: String, query: String, order: String): Either<Failure, NewsBody> {
        return newsService.getBusinessNews(key, query, order).awaitCall()
    }

    override suspend fun getSportNews(key: String, query: String, order: String): Either<Failure, NewsBody> {
        return newsService.getSportNews(key, query, order).awaitCall()
    }

    override suspend fun getPoliticsNews(key: String, query: String, order: String): Either<Failure, NewsBody> {
        return newsService.getPoliticsNews(key, query, order).awaitCall()
    }
}