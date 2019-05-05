package com.bytepace.ipr.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.bytepace.ipr.database.model.Currency

@Dao
interface CurrencyDao: BaseDao<Currency> {

    @Query("Select ifnull(max(version), 0) from Currency")
    fun getLastVersion(): Long

    @Query("Select count(*) from Currency where not is_hidden and not is_removed")
    fun getCount(): Int

    @Query("Select * from Currency where not is_hidden and not is_removed")
    fun getAll(): List<Currency>
}