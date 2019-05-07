package com.bytepace.ipr.database.model.only_xml

import com.bytepace.ipr.database.ERROR
import com.bytepace.ipr.database.NAME
import com.bytepace.ipr.database.OBJECTS
import com.bytepace.ipr.database.OBJECTS_COUNT
import com.bytepace.ipr.database.model.BaseDBObject
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList

abstract class XmlListObjects {
    @get:Element(name = OBJECTS_COUNT)
    @set:Element(name = OBJECTS_COUNT)
    var count = 0

    @get:Element(name = NAME)
    @set:Element(name = NAME)
    var name = ""

    @get:Element(name = ERROR, required = false)
    @set:Element(name = ERROR, required = false)
    var error = ""
}