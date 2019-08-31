package com.example.newscontroller.helper

import com.example.newscontroller.module.*
import org.koin.dsl.module.Module

class DependencyInjection {

    companion object{

        fun getModuleList(): List<Module>{
            val list = arrayListOf<Module>()
            list.add(networkModule)
            list.add(repoModule)
            list.add(viewModule)
            list.add(sharedModule)
            list.add( dataBaseModule )
            return list
        }
    }
}