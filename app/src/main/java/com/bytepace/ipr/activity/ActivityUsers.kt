package com.bytepace.ipr.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import com.bytepace.ipr.App
import com.bytepace.ipr.R
import com.bytepace.ipr.UsersAdapter
import com.bytepace.ipr.database.model.relation.UserRelation
import com.bytepace.ipr.utils.BaseNonPaginationAdapter
import kotlinx.android.synthetic.main.activity_users.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ActivityUsers: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = UsersAdapter(object: BaseNonPaginationAdapter.NonPaginationAdapterCallback<UserRelation> {
            override fun onItemClick(item: UserRelation) {
                showConfirmationAlert("sex: ${item.sex}\n" +
                        "email: ${item.email}", "More info"){
                    //nothing
                }
            }
        })
        recyclerView.adapter = adapter
        doAsync {
            val users = App.database.users().getAll()
            uiThread {
                adapter.addItems(users)
            }
        }
    }
}