package com.example.newscontroller.base

import androidx.lifecycle.ViewModel
import com.example.newscontroller.core.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    private val coroutineJob: MutableList<Job> = mutableListOf(),
    val generalObserver: SingleLiveEvent<Failure> = SingleLiveEvent()
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    protected fun addJob(job: Job) {
        coroutineJob.add(job)
    }

    private fun cancelJob(job: Job){
        job.cancel()
        coroutineJob.remove(job)
    }

    private fun cancelJobs(){
        coroutineJob.clear()
    }

    protected fun showFailure(failure: Failure){
        generalObserver.setValue(failure)
    }

    override fun onCleared() {
        cancelJobs()
        super.onCleared()
    }
}