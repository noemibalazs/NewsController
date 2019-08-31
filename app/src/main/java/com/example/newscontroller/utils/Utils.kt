package com.example.newscontroller.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.newscontroller.R
import com.example.newscontroller.data.NewsEntity
import com.example.newscontroller.data.NewsNews

fun Context.openActivity(dest: Class<*>){
    return this.startActivity(Intent(this, dest))
}

fun Context.loadPicture(link: String, image: ImageView){
    Glide.with(this)
        .load(link)
        .error(R.drawable.back)
        .placeholder(R.drawable.back)
        .into(image)
}

fun Context.getDrawableUri(): String{
    val uri = Uri.parse("android.resource://" + this.packageName + "drawable/back")
    return uri.toString()
}

fun Context.news2Entity(news: NewsNews): NewsEntity{
    if (news.thumbnail != null){
        return NewsEntity(news.headline,news.byline, news.lastModified, news.shortUrl, news.thumbnail, news.bodyText )
    }else{
        return NewsEntity(news.headline, news.byline, news.lastModified, news.shortUrl, this.getDrawableUri(), news.bodyText )
    }
}