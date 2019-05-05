package com.bytepace.ipr.database.model.only_xml

import com.bytepace.ipr.database.OBJECTS
import com.bytepace.ipr.database.model.Currency
import org.simpleframework.xml.ElementList
import java.io.Serializable

class CurrencyList: XmlListObjects(), Serializable {
    @get:ElementList(inline = true, entry = OBJECTS, required = false)
    @set:ElementList(inline = true, entry = OBJECTS, required = false)
    var objects = ArrayList<Currency>()
}