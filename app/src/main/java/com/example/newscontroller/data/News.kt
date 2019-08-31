package com.example.newscontroller.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class News(
    @field:SerializedName("fields") val fields: NewsNews
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readParcelable<NewsNews>(NewsNews::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeParcelable(fields, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<News> = object : Parcelable.Creator<News> {
            override fun createFromParcel(source: Parcel): News = News(source)
            override fun newArray(size: Int): Array<News?> = arrayOfNulls(size)
        }
    }
}