package com.bytepace.ipr.database.model.relation

import android.arch.persistence.room.Relation
import com.bytepace.ipr.database.CURRENCY
import com.bytepace.ipr.database.ID
import com.bytepace.ipr.database.model.Currency
import com.bytepace.ipr.database.model.Device
import java.io.Serializable

class DeviceRelation: Device(), Serializable {
    @Relation(parentColumn = CURRENCY, entityColumn = ID, entity = Currency::class)
    var currencies: List<Currency> = ArrayList()

    val currency: Currency?
        get() {
            if (currencies.isEmpty()) return null
            return currencies[0]
        }
}