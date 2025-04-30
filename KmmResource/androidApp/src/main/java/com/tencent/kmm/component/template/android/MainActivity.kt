package com.tencent.kmm.component.template.android

import android.app.Activity
import android.os.Bundle
import com.tencent.kmm.component.template.android.databinding.MainBinding

class MainActivity : Activity() {

    val binding by lazy {
        MainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}
