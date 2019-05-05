package com.bytepace.ipr.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.bytepace.ipr.database.converters.StringArrayConverter
import com.bytepace.ipr.database.dao.CurrencyDao
import com.bytepace.ipr.database.dao.DeviceDao
import com.bytepace.ipr.database.dao.UserDao
import com.bytepace.ipr.database.model.Currency
import com.bytepace.ipr.database.model.Device
import com.bytepace.ipr.database.model.User

const val ID = "id"
const val VERSION = "version"
const val IS_REMOVED = "is_removed"
const val IS_HIDDEN = "is_hidden"
const val IS_TEST = "is_test"
const val NAME = "name"
const val LOGIN = "login"
const val PASSWORD = "password"
const val EMAIL = "email"
const val DATE = "date"
const val DEVICE = "device"
const val DATE_OF_BIRTH = "date_of_birth"
const val COLOR = "color"
const val SEX = "sex"
const val MALE = "male"
const val FEMALE = "female"
const val MOBILE_PHONES = "mobile_phones"
const val PLATFORM = "platform"
const val PLATFORM_VERSION = "platform_version"
const val COST = "cost"
const val SCREEN_DIAGONAL = "screen_diagonal"
const val ICON = "icon"
const val CURRENCY = "currency"
const val ERROR = "error"
const val OBJECTS = "objects"
const val OBJECTS_COUNT = "objects_count"

const val TRUE = "true"

const val DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
const val DATE_FORMAT = "yyyy-MM-dd"

@Database(entities = arrayOf(User::class, Device::class, Currency::class), version = 1)

@TypeConverters(StringArrayConverter::class)

abstract class AppDatabase: RoomDatabase() {
    abstract fun users(): UserDao
    abstract fun devices(): DeviceDao
    abstract fun currencies(): CurrencyDao
}