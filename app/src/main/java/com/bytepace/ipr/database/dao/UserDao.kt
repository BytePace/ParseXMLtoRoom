package com.bytepace.ipr.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.bytepace.ipr.database.model.User
import com.bytepace.ipr.database.model.relation.UserRelation

@Dao
interface UserDao: BaseDao<User> {

    @Query("Select ifnull(max(version), 0) from User")
    fun getLastVersion(): Long

    @Query("Select count(*) from User where not is_hidden and not is_removed")
    fun getCount(): Int

    @Transaction
    @Query("Select * from User where not is_hidden and not is_removed")
    fun getAll(): List<UserRelation>
}