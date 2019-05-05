package com.bytepace.ipr.database.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import com.bytepace.ipr.database.*
import org.simpleframework.xml.Element
import java.io.Serializable

@Entity
open class Device: BaseDBObject(), Serializable {
    @get:Element(name = PLATFORM)
    @set:Element(name = PLATFORM)
    @ColumnInfo(name = PLATFORM)
    var platform: String = ""

    @get:Element(name = PLATFORM_VERSION)
    @set:Element(name = PLATFORM_VERSION)
    @ColumnInfo(name = PLATFORM_VERSION)
    var platformVersion: String = ""

    @get:Element(name = SCREEN_DIAGONAL)
    @set:Element(name = SCREEN_DIAGONAL)
    @ColumnInfo(name = SCREEN_DIAGONAL)
    var screenDiagonal: String = ""

    @get:Element(name = COLOR)
    @set:Element(name = COLOR)
    @Ignore
    var colorString: String = ""
        set(value) {
            color = parseColor(value)
        }

    @get:Element(name = DATE)
    @set:Element(name = DATE)
    @Ignore
    var dateString: String = ""
        set(value) {
            date = parseStringDateToLong(value, DATE_TIME_FORMAT)
        }

    @ColumnInfo(name = DATE)
    var date: Long = 0

    @ColumnInfo(name =  COLOR)
    var color: Int = 0

    @get:Element(name = COST)
    @set:Element(name = COST)
    @ColumnInfo(name =  COST)
    var cost: Double = 0.0

    @get:Element(name = CURRENCY)
    @set:Element(name = CURRENCY)
    @ColumnInfo(name =  CURRENCY)
    var currencyID: String = ""
}