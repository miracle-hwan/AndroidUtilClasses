package com.miraclehwan.androidutils.view.main

import androidx.databinding.ObservableField
import com.miraclehwan.androidutils.view.main.model.ClassInfo
import com.miraclehwan.androidutils.view.glide.GlideActivity
import com.miraclehwan.androidutils.view.shadow.ShadowActivity
import com.miraclehwan.androidutils.viewmodel.BaseViewModel
import io.reactivex.subjects.PublishSubject

class MainViewModel : BaseViewModel() {

    val items = ObservableField<List<ClassInfo>>(
        mutableListOf(
            ClassInfo("1. ShadowView", ShadowActivity::class.java),
            ClassInfo("2. Glide", GlideActivity::class.java)
        )
    )

    val intentObservable = PublishSubject.create<Class<*>>()

    val onItemClick = fun(intentClass: Class<*>) {
        intentObservable.onNext(intentClass)
    }
}