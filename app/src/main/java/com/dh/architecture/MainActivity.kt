package com.dh.architecture

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main_test_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_test_activity)

        liveDataBusBtn.setOnClickListener { startActivity(Intent(this, LiveDataBusFirstActivity::class.java)) }
        parallelBtn.setOnClickListener { startActivity(Intent(this, ParallelTestActivity::class.java)) }
    }}