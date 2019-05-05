package com.bytepace.ipr.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.bytepace.ipr.database.model.Device
import com.bytepace.ipr.database.model.relation.DeviceRelation

@Dao
interface DeviceDao: BaseDao<Device> {

    @Query("Select ifnull(max(version), 0) from Device")
    fun getLastVersion(): Long

    @Query("Select count(*) from Device where not is_hidden and not is_removed")
    fun getCount(): Int

    @Transaction
    @Query("Select * from Device where not is_hidden and not is_removed")
    fun getAll(): List<DeviceRelation>
}