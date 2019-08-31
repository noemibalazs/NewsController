package com.example.newscontroller.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newscontroller.R
import com.example.newscontroller.data.News
import com.example.newscontroller.data.NewsBody
import com.example.newscontroller.data.NewsEntity
import com.example.newscontroller.helper.SharedPrefHelper
import com.example.newscontroller.room.NewsDAO
import com.example.newscontroller.ui.NewsDetails
import com.example.newscontroller.utils.getDrawableUri
import com.example.newscontroller.utils.loadPicture
import com.example.newscontroller.utils.news2Entity
import com.example.newscontroller.utils.openActivity
import org.jetbrains.anko.doAsync
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.logging.Logger

class NewsAdapter (val myList: MutableList<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(), KoinComponent {

    private val helper: SharedPrefHelper by inject()
    private val dataBase: NewsDAO by  inject()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = myList[position]
        val fields = news.fields
        if (fields.thumbnail != null){
            holder.imageHolder.context.loadPicture(fields.thumbnail, holder.imageHolder)
        }else{
            holder.imageHolder.context.loadPicture(holder.imageHolder.context.getDrawableUri(), holder.imageHolder)
        }

        holder.dateHolder.text = fields.lastModified.take(10)
        holder.titleHolder.text = fields.headline

        holder.imageHolder.setOnClickListener {
            helper.saveNewsId(news.fields.headline)
            val entity = holder.imageHolder.context.news2Entity(news.fields)
            saveNews(entity)
            holder.imageHolder.context.openActivity(NewsDetails::class.java)
        }
    }


    inner class NewsViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageHolder = view.findViewById<ImageView>(R.id.newsAvatar)
        val titleHolder = view.findViewById<TextView>(R.id.newsTitle)
        val dateHolder = view.findViewById<TextView>(R.id.newsDate)
    }

    private fun saveNews( entity: NewsEntity){
        doAsync {
            dataBase.addEntity(entity)
        }
    }
}