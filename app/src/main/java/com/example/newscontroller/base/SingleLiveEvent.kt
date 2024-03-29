package com.example.newscontroller.base

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.orhanobut.logger.Logger
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T>(
    private val pending: AtomicBoolean = AtomicBoolean(false)
): MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers())
            Logger.d("Many observers were registered but only one will be notified on changes")
        super.observe(owner, Observer {
            if (pending.compareAndSet(true, false))
                observer.onChanged(it)
        })
    }

    @MainThread
    override fun setValue(value: T) {
        pending.set(true)
        super.setValue(value)
    }

}