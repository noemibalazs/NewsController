package com.example.newscontroller.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class NewsNews(
    @field:SerializedName("headline") val headline: String,
    @field:SerializedName("byline") val byline: String,
    @field:SerializedName("lastModified") val lastModified: String,
    @field:SerializedName("shortUrl") val shortUrl: String,
    @field:SerializedName("thumbnail") val thumbnail: String? = null,
    @field:SerializedName("bodyText") val bodyText: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(headline)
        writeString(byline)
        writeString(lastModified)
        writeString(shortUrl)
        writeString(thumbnail)
        writeString(bodyText)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<NewsNews> = object : Parcelable.Creator<NewsNews> {
            override fun createFromParcel(source: Parcel): NewsNews = NewsNews(source)
            override fun newArray(size: Int): Array<NewsNews?> = arrayOfNulls(size)
        }
    }
}