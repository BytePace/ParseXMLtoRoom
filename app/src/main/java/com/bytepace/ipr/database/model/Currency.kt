package com.bytepace.ipr.database.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.graphics.Bitmap
import com.bytepace.ipr.database.ICON
import org.simpleframework.xml.Element
import java.io.Serializable

@Entity
class Currency: BaseDBObject(), Serializable {
    @get:Element(name = ICON)
    @set:Element(name = ICON)
    @ColumnInfo(name =  ICON)
    var iconBase64: String = ""

    val icon: Bitmap
        get() {
            return parseBitmapFromBase64(iconBase64)
        }
}