package com.example.newscontroller.module

import com.example.newscontroller.core.NewsSource
import com.example.newscontroller.core.NewsSourceImpl
import com.example.newscontroller.helper.SharedPrefHelper
import com.example.newscontroller.network.NewsService
import com.example.newscontroller.repository.BusinessRepository
import com.example.newscontroller.repository.PoliticsRepository
import com.example.newscontroller.repository.SportsRepository
import com.example.newscontroller.room.NewsDAO
import com.example.newscontroller.viewmodel.BusinessViewModel
import com.example.newscontroller.viewmodel.PoliticsViewModel
import com.example.newscontroller.viewmodel.SportsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import kotlin.math.sin


val networkModule = module {
    single { NewsService.getRetrofitInstance() }
}

val repoModule = module {
    single<NewsSource> { NewsSourceImpl(newsService = get())  }
    single { BusinessRepository(newsSource = get()) }
    single { SportsRepository(newsSource = get()) }
    single { PoliticsRepository(newsSource = get()) }
}

val viewModule = module {
    single { BusinessViewModel( businessRepository = get()) }
    single { SportsViewModel(sportsRepository = get()) }
    single { PoliticsViewModel(get()) }
}

val sharedModule = module {
    single { SharedPrefHelper(androidContext()) }
}

val dataBaseModule = module {
    single { NewsDAO.getNewsDB(androidContext()) }
}