package com.bytepace.ipr.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.bytepace.ipr.R

class ActivityUsers: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_users)
    }
}