package com.bytepace.ipr.database.model.relation

import android.arch.persistence.room.Relation
import com.bytepace.ipr.database.DEVICE
import com.bytepace.ipr.database.ID
import com.bytepace.ipr.database.model.Device
import com.bytepace.ipr.database.model.User
import java.io.Serializable

class UserRelation: User(), Serializable {
    @Relation(parentColumn = DEVICE, entityColumn = ID, entity = Device::class)
    var devices: List<Device> = ArrayList()

    val device: Device?
        get() {
            if (devices.isEmpty()) return null
            return devices[0]
        }
}