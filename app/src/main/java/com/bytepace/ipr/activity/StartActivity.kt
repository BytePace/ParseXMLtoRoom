package com.bytepace.ipr.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bytepace.ipr.App
import com.bytepace.ipr.R
import com.bytepace.ipr.database.model.only_xml.CurrencyList
import com.bytepace.ipr.database.model.only_xml.DeviceList
import com.bytepace.ipr.database.model.only_xml.UserList
import com.bytepace.ipr.utils.CURRENCY
import com.bytepace.ipr.utils.DEVICES
import com.bytepace.ipr.utils.USERS
import com.bytepace.ipr.utils.XmlParser
import kotlinx.android.synthetic.main.activity_start.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class StartActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        buttonParser.setOnClickListener {
            startParse(false)
        }

        buttonPullParser.setOnClickListener {
            startParse(true)
        }
    }

    private fun startParse(isPullParser: Boolean) {
        progressBar.visibility = View.VISIBLE
        buttonParser.isEnabled = false
        buttonPullParser.isEnabled = false
        val timeBegin = Calendar.getInstance().timeInMillis
        doAsync {
            val users = XmlParser.parseXml(USERS, "return", UserList::class.java)
            val currencies = XmlParser.parseXml(CURRENCY, "return", CurrencyList::class.java)
            val devices = XmlParser.parseXml(DEVICES, "return", DeviceList::class.java)
            if (users?.objects != null && users.objects.isNotEmpty()) {
                App.database.users().insertAll(users.objects)
            }
            if (currencies?.objects != null && currencies.objects.isNotEmpty()) {
                App.database.currencies().insertAll(currencies.objects)
            }
            if (devices?.objects != null && devices.objects.isNotEmpty()) {
                App.database.devices().insertAll(devices.objects)
            }
            uiThread {
                val timeEnd = Calendar.getInstance().timeInMillis
                progressBar.visibility = View.GONE
                val parseTime = timeEnd - timeBegin
                showConfirmationAlert("Parse time: $parseTime ms", "Parsing successfull") {
                    startActivity(Intent(this@StartActivity, ActivityUsers::class.java))
                }
            }
        }
    }
}