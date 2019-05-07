package com.bytepace.ipr.database.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import com.bytepace.ipr.database.*
import com.bytepace.ipr.utils.HashUtil
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import java.io.Serializable

@Entity
open class User: BaseDBObject(), Serializable {

    @get:Element(name = LOGIN)
    @set:Element(name = LOGIN)
    @ColumnInfo(name = LOGIN)
    var login: String = ""

    @get:Element(name = PASSWORD)
    @set:Element(name = PASSWORD)
    @ColumnInfo(name = PASSWORD)
    var password: String = ""

    @get:Element(name = EMAIL)
    @set:Element(name = EMAIL)
    @ColumnInfo(name = EMAIL)
    var email: String = ""

    @get:Element(name = DATE_OF_BIRTH)
    @set:Element(name = DATE_OF_BIRTH)
    @Ignore
    var dateOfBirthString: String = ""
        set(value) {
            dateOfBirth = parseStringDateToLong(value, DATE_FORMAT)
        }

    @ColumnInfo(name = DATE_OF_BIRTH)
    var dateOfBirth: Long = 0

    @get:Element(name = DEVICE)
    @set:Element(name = DEVICE)
    @ColumnInfo(name = DEVICE)
    var deviceID: String = ""

    @get:Element(name = SEX)
    @set:Element(name = SEX)
    @ColumnInfo(name = SEX)
    var sex: String = ""

    @get:ElementList(inline = true, entry = MOBILE_PHONES)
    @set:ElementList(inline = true, entry = MOBILE_PHONES)
    @ColumnInfo(name = MOBILE_PHONES)
    var mobilePhones: ArrayList<String> = ArrayList()
}