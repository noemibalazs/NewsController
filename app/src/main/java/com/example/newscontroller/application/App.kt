package com.example.newscontroller.application

import android.app.Application
import com.example.newscontroller.helper.DependencyInjection
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(this)
        initLogger()

    }


    private fun initKoin(application: Application){
        startKoin(
            application,
            DependencyInjection.getModuleList()
        )
    }

    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
            .tag("CONTROLLER_LOGGER")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()

        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }
}