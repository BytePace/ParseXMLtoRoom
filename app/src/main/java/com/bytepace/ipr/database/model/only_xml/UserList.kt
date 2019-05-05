package com.bytepace.ipr.database.model.only_xml

import com.bytepace.ipr.database.OBJECTS
import com.bytepace.ipr.database.model.Currency
import com.bytepace.ipr.database.model.Device
import com.bytepace.ipr.database.model.User
import org.simpleframework.xml.ElementList
import java.io.Serializable

class UserList: XmlListObjects(), Serializable {
    @get:ElementList(inline = true, entry = OBJECTS, required = false)
    @set:ElementList(inline = true, entry = OBJECTS, required = false)
    var objects = ArrayList<User>()
}