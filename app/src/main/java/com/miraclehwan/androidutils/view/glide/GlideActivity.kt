package com.miraclehwan.androidutils.view.glide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.miraclehwan.androidutils.R
import com.miraclehwan.androidutils.databinding.ActivityGlideBinding

class GlideActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityGlideBinding>(this, R.layout.activity_glide)
    }
}