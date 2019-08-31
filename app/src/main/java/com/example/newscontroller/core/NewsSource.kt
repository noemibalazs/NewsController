package com.example.newscontroller.core

import com.example.newscontroller.data.NewsBody

interface NewsSource {

    suspend fun getBusinessNews(key: String, query: String, order: String): Either<Failure, NewsBody>

    suspend fun getSportNews(key: String, query: String, order: String): Either<Failure, NewsBody>

    suspend fun getPoliticsNews(key: String, query: String, order: String): Either<Failure, NewsBody>
}