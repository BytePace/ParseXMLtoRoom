package com.bytepace.ipr.database.dao

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update
import com.bytepace.ipr.database.model.BaseDBObject

interface BaseDao<T: BaseDBObject> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: ArrayList<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T)

    @Update
    fun updateAll(items: ArrayList<T>)

    @Update
    fun update(item: T)

    @Delete
    fun deleteAll(items: ArrayList<T>)

    @Delete
    fun delete(item: T)
}