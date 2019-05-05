package com.bytepace.ipr.database.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.Base64
import com.bytepace.ipr.database.*
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseDBObject {
    @get:Attribute(name = ID)
    @set:Attribute(name = ID)
    @PrimaryKey(autoGenerate = false)
    var id: String = ""

    @get:Attribute(name = VERSION)
    @set:Attribute(name = VERSION)
    @ColumnInfo(name = VERSION)
    var version: Long = 0

    @get:Attribute(name = IS_REMOVED, required = false)
    @set:Attribute(name = IS_REMOVED, required = false)
    @ColumnInfo(name = IS_REMOVED)
    var isRemoved: Boolean = false

    @get:Attribute(name = IS_HIDDEN, required = false)
    @set:Attribute(name = IS_HIDDEN, required = false)
    @ColumnInfo(name = IS_HIDDEN)
    var isHidden: Boolean = false

    @get:Attribute(name = IS_TEST, required = false)
    @set:Attribute(name = IS_TEST, required = false)
    @ColumnInfo(name = IS_TEST)
    var isTest: Boolean = false

    @get:Element(name = NAME)
    @set:Element(name = NAME)
    @ColumnInfo(name = NAME)
    var name: String = ""

    open fun isVisible() : Boolean {
        return !isHidden && !isRemoved
    }

    open fun set(key: String, value: String) {
        when (key) {
            ID -> {
                id = value
            }

            NAME -> {
                name = value
            }

            IS_REMOVED -> {
                isRemoved = value.contains(TRUE)
            }

            IS_HIDDEN -> {
                isHidden = value.contains(TRUE)
            }

            VERSION -> {
                version = value.toLong()
            }

            else -> {
                // NOTHING
            }
        }
    }

    open fun setArray(key: String, value: ArrayList<String>) {
        // overrides in child nodes
    }

    companion object {
        fun parseBitmapFromBase64(pic: String): Bitmap {
            val thumb = Base64.decode(pic.toByteArray(), Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(thumb, 0, thumb.size)
        }

        fun parseColor(colorCode: String): Int {
            return Color.parseColor("#$colorCode")
        }

        fun parseStringDateToLong(value: String, format: String = DATE_TIME_FORMAT): Long {
            if (format == DATE_FORMAT && value == "0001-01-01") {
                return 0
            }
            val formatter = SimpleDateFormat(format, Locale.getDefault())
            return formatter.parse(value).time
        }
    }
}