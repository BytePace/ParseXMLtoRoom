package com.bytepace.ipr.utils

import com.bytepace.ipr.database.model.BaseDBObject
import com.bytepace.ipr.database.model.only_xml.XmlListObjects
import org.simpleframework.xml.core.Persister

class XmlParser {
    companion object {

        fun cutXML(xml: String, tag: String): String {
            val start = xml.indexOf(tag)
            val end = xml.lastIndexOf(tag)
            if (start != -1 && end != -1) {
                val cutStart = start - 1
                val cutEnd = xml.indexOf(">", end) + 1
                return xml.substring(cutStart, cutEnd)
            } else {
                return ""
            }
        }

        fun getEntries(line: String, toSearch: String): Int {
            return (line.length - line.replace(toSearch, "").length) / toSearch.length
        }

        fun <T: XmlListObjects> parseXml(xml: String?, contentTag: String, tClass: Class<T>): T? {
            val serialiser = Persister()
            val result : T?
            if (xml != null) {
                val resultXml = cutXML(xml, contentTag)
                result = serialiser.read(tClass, resultXml, false)
            } else {
                result = null
            }
            return result
        }

        fun <T: XmlListObjects> parsePull(xml: String?, contentTag: String, tClass: Class<T>): T? {
            val serialiser = Persister()
            val result : T?
            if (xml != null) {
                val resultXml = cutXML(xml, contentTag)
                result = serialiser.read(tClass, resultXml, false)
            } else {
                result = null
            }
            return result
        }
    }
}