package com.miraclehwan.androidutils.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.miraclehwan.androidutils.R
import com.miraclehwan.androidutils.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            vm = ViewModelProvider(this@MainActivity)[MainViewModel::class.java].apply {
                addDisposable(intentObservable.subscribe { intentClass ->
                    startActivity(Intent(this@MainActivity, intentClass))
                })
            }
        }
    }
}