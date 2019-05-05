package com.bytepace.ipr.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.bytepace.ipr.R
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_start)

        buttonParser.setOnClickListener {
            startParse(false)
        }

        buttonPullParser.setOnClickListener {
            startParse(true)
        }
    }

    private fun parseElems() {

    }

    private fun startParse(isPullParser: Boolean) {
        progressBar.visibility = View.VISIBLE
        buttonParser.isEnabled = false
        buttonPullParser.isEnabled = false
    }
}