package com.example.newscontroller.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.newscontroller.R
import com.example.newscontroller.adapter.NewsAdapter
import com.example.newscontroller.base.BaseFragment
import com.example.newscontroller.viewmodel.BusinessViewModel
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_container.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BusinessFragment : BaseFragment() {

    override val viewModel: BusinessViewModel by viewModel()
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun setUpViewAfterOnViewCreated(view: View) {
        initSearchHint()
        searchRecycleView.setHasFixedSize(true)
        searchDone.setOnClickListener {
            if (isValid()){
                initSearch()
            }else{
                toast()
                return@setOnClickListener
            }
        }

    }

    override fun setUpObservables(lifecycleOwner: LifecycleOwner) {
        viewModel.getBusinessData().observe(lifecycleOwner, Observer {
            Logger.d("Results is: ${it.response.results}")
            adapter = NewsAdapter(it.response.results)
            searchRecycleView.adapter = adapter
        })
    }

    private fun initSearch(){
        viewModel.setSearchedText(searchNews.text.toString().trim())
        viewModel.loadBusinessData()
        adapter.notifyDataSetChanged()
        searchNews.setText("")

    }

    private fun isValid():Boolean{
        return searchNews.text.toString().length > 3
    }

    private fun initSearchHint(){
        searchNews.hint = getString(R.string.search_hint_business)
    }

    private fun toast(){
        Toast.makeText(activity, getString(R.string.toast_message), Toast.LENGTH_LONG).show()
    }
}