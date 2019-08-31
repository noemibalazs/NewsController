package com.example.newscontroller.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.newscontroller.R
import com.example.newscontroller.adapter.EntityAdapter
import com.example.newscontroller.base.BaseFragment
import com.example.newscontroller.room.NewsDAO
import kotlinx.android.synthetic.main.fragment_visited.*
import org.koin.android.ext.android.inject

class VisitedFragment : BaseFragment() {

    private val newsDao: NewsDAO by inject()
    private lateinit var adapter: EntityAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_visited, container, false)
    }
    override fun setUpViewAfterOnViewCreated(view: View) {
        visitedRecycleView.setHasFixedSize(true)
    }

    override fun setUpObservables(lifecycleOwner: LifecycleOwner) {
        newsDao.getNewsList().observe(lifecycleOwner, Observer {
            adapter = EntityAdapter(it)
            visitedRecycleView.adapter = adapter
        })
    }
}