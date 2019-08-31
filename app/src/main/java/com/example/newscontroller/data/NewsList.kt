package com.example.newscontroller.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class NewsList(
    @field: SerializedName("results") val results: MutableList<News>
) : Parcelable {
    constructor(source: Parcel) : this(
        ArrayList<News>().apply { source.readList(this, News::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeList(results)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<NewsList> = object : Parcelable.Creator<NewsList> {
            override fun createFromParcel(source: Parcel): NewsList = NewsList(source)
            override fun newArray(size: Int): Array<NewsList?> = arrayOfNulls(size)
        }
    }
}