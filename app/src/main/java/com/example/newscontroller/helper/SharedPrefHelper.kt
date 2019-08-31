package com.example.newscontroller.helper

import android.content.Context
import com.example.newscontroller.utils.NEWS_ID

class SharedPrefHelper(context: Context){

    val shared = context.getSharedPreferences("My preference", Context.MODE_PRIVATE)

    fun saveNewsId(key: String){
        val editor = shared.edit()
        editor.putString(NEWS_ID, key)
        editor.apply()
    }

    fun getNewsId(): String{
        return shared.getString(NEWS_ID, "")
    }
}