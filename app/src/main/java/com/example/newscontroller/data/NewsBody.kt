package com.example.newscontroller.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class NewsBody(
    @field: SerializedName("response") val response: NewsList
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readParcelable<NewsList>(NewsList::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(response, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<NewsBody> = object : Parcelable.Creator<NewsBody> {
            override fun createFromParcel(source: Parcel): NewsBody = NewsBody(source)
            override fun newArray(size: Int): Array<NewsBody?> = arrayOfNulls(size)
        }
    }
}