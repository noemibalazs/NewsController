package com.example.newscontroller.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.newscontroller.R
import com.example.newscontroller.data.NewsEntity
import com.example.newscontroller.helper.SharedPrefHelper
import com.example.newscontroller.room.NewsDAO
import com.example.newscontroller.utils.loadPicture
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.news_item.*
import org.koin.android.ext.android.inject

class NewsDetails : AppCompatActivity() {

    private val helper: SharedPrefHelper by inject()
    private val newsDao: NewsDAO by inject()

    private lateinit var entity: NewsEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val entityObserver = object: Observer<NewsEntity>{
            override fun onChanged(news: NewsEntity?) {
                news?.let {
                    entity = it
                    setUpUI(it)
                }
            }

        }
        newsDao.getNewsById(helper.getNewsId()).observe(this, entityObserver)

        fab_button.setOnClickListener {
            shareNewsLink()
        }
    }

    private fun setUpUI(entity: NewsEntity){
        loadPicture(entity.picture, news_cover)
        news_date.text = entity.date.take(10)
        news_byline.text = String.format(getString(R.string.news_created_by), entity.author)
        news_title.text = entity.headLine
        news_description.text = entity.description
    }

    private fun shareNewsLink(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.apply {
            setType("text/plain")
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.news_deserve_2_read))
            putExtra(Intent.EXTRA_TEXT, entity.url)
        }
        startActivity(Intent.createChooser(intent, "Your choice"))
    }


}
