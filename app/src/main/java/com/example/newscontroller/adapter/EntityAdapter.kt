package com.example.newscontroller.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newscontroller.R
import com.example.newscontroller.data.NewsEntity
import com.example.newscontroller.helper.SharedPrefHelper
import com.example.newscontroller.ui.NewsDetails
import com.example.newscontroller.utils.loadPicture
import com.example.newscontroller.utils.openActivity
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class EntityAdapter(val myList: MutableList<NewsEntity>): RecyclerView.Adapter<EntityAdapter.EntityViewHolder>(), KoinComponent {

    private val helper: SharedPrefHelper by inject()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return EntityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = myList[position]
        holder.imageHolder.context.loadPicture(entity.picture, holder.imageHolder)
        holder.dateHolder.text = entity.date.take(10)
        holder.titleHolder.text = entity.headLine

        holder.imageHolder.setOnClickListener {
            helper.saveNewsId(entity.headLine)
            holder.imageHolder.context.openActivity(NewsDetails::class.java)
        }

    }

    inner class EntityViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageHolder = view.findViewById<ImageView>(R.id.newsAvatar)
        val titleHolder = view.findViewById<TextView>(R.id.newsTitle)
        val dateHolder = view.findViewById<TextView>(R.id.newsDate)
   }
}