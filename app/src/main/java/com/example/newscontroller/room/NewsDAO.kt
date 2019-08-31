package com.example.newscontroller.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newscontroller.data.NewsEntity
import com.example.newscontroller.utils.NEWS_DB

@Dao
interface NewsDAO {

    @Query("SELECT * FROM news")
    fun getNewsList(): LiveData<MutableList<NewsEntity>>

    @Query("SELECT *FROM news WHERE headLine = :id")
    fun getNewsById(id: String): LiveData<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEntity(entity: NewsEntity)

    @Delete
    fun deleteEntity(entity: NewsEntity)

    companion object{

        fun getNewsDB(context: Context):NewsDAO{
            return Room.databaseBuilder(context, NewsDataBase::class.java, NEWS_DB).build().newsDAO()
        }
    }
}