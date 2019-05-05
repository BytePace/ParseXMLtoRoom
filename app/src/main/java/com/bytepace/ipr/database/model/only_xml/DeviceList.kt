package com.bytepace.ipr.database.model.only_xml

import com.bytepace.ipr.database.OBJECTS
import com.bytepace.ipr.database.model.Currency
import com.bytepace.ipr.database.model.Device
import org.simpleframework.xml.ElementList
import java.io.Serializable

class DeviceList: XmlListObjects(), Serializable {
    @get:ElementList(inline = true, entry = OBJECTS, required = false)
    @set:ElementList(inline = true, entry = OBJECTS, required = false)
    var objects = ArrayList<Device>()
}