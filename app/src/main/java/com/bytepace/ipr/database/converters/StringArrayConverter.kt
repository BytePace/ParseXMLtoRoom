package com.bytepace.ipr.database.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson

class StringArrayConverter {
    @TypeConverter
    fun listToJson(value: ArrayList<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): ArrayList<String>? {
        val result = ArrayList<String>()
        for (item in Gson().fromJson(value, Array<String>::class.java)) {
            result.add(item)
        }
        return result
    }
}