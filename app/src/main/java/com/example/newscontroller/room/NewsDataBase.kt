package com.example.newscontroller.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newscontroller.data.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun newsDAO(): NewsDAO
}