package com.example.newscontroller.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    @field:SerializedName("headline") val headLine: String,
    @field:SerializedName("byline") val author: String,
    @field:SerializedName("lastModified") val date: String,
    @field:SerializedName("shortUrl") val url: String,
    @field:SerializedName("thumbnail") val picture: String,
    @field:SerializedName("bodyText") val description: String
)