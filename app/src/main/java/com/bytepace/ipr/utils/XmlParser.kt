package com.bytepace.ipr.utils

import com.bytepace.ipr.database.*
import com.bytepace.ipr.database.model.BaseDBObject
import com.bytepace.ipr.database.model.only_xml.XmlListObjects
import org.simpleframework.xml.core.Persister
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.lang.Exception

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

        fun <T: BaseDBObject> parsePull(xml: String?, contentTag: String, instance: () ->T): ArrayList<T>? {
            val result: ArrayList<T>?
            if (xml != null) {
                val resultXml = cutXML(xml, contentTag)
                result = parseXmlByKeyValue(ByteArrayInputStream(resultXml.toByteArray()), instance)
            } else {
                result = null
            }
            return result
        }

        private fun <T: BaseDBObject> parseXmlByKeyValue(stream: InputStream, instance: () -> T): ArrayList<T>?{
            val result = ArrayList<T>()
            var item = instance()
            var stringArrayList: ArrayList<String>? = null
            var stringArrayType = ""

            try {
                val parser = XmlPullParserFactory.newInstance().newPullParser()
                parser.setInput(stream, "UTF-8")
                parser.require(XmlPullParser.START_DOCUMENT, null, null)
                var eventType = parser.eventType
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    val pName = parser.name
                    when (eventType) {
                        XmlPullParser.START_TAG -> {
                            when (pName) {
                                OBJECTS_COUNT -> {
                                    if (parser.nextText().toInt() == 0) {
                                        return result
                                    }
                                }

                                OBJECTS -> {
                                    item = instance()

                                    val id = parser.getAttributeValue(null, ID)
                                    val version = parser.getAttributeValue(null, VERSION)
                                    val isRemoved = parser.getAttributeValue(null, IS_REMOVED)
                                    val isHidden = parser.getAttributeValue(null, IS_HIDDEN)
                                    val isTest = parser.getAttributeValue(null, IS_TEST)

                                    if (id != null) {
                                        item.set(ID, id)
                                    }
                                    if (version != null) {
                                        item.set(VERSION, version)
                                    }
                                    if (isRemoved != null) {
                                        item.set(IS_REMOVED, isRemoved)
                                    }
                                    if (isHidden != null) {
                                        item.set(IS_HIDDEN, isHidden)
                                    }
                                    if (isTest != null) {
                                        item.set(IS_TEST, isTest)
                                    }
                                }

                                NAME, LOGIN, PASSWORD, EMAIL, DATE_OF_BIRTH, DEVICE, SEX, PLATFORM, PLATFORM_VERSION,
                                SCREEN_DIAGONAL, COLOR, DATE, COST, CURRENCY, ICON -> {
                                    item.set(pName, parser.nextText())
                                }

                                MOBILE_PHONES -> {
                                    if (stringArrayType.isEmpty()) {
                                        stringArrayList = ArrayList()
                                        stringArrayType = pName
                                    } else {
                                        if (stringArrayType != pName) {
                                            stringArrayList?.let {
                                                item.setArray(stringArrayType, it)
                                            }
                                            stringArrayList = ArrayList()
                                            stringArrayType = pName
                                        }
                                    }
                                    stringArrayList?.add(parser.nextText())
                                }
                            }
                        }

                        XmlPullParser.END_TAG -> {
                            when (pName) {
                                OBJECTS -> {
                                    if (stringArrayType.isNotEmpty() && stringArrayList != null && stringArrayList.isNotEmpty()) {
                                        item.setArray(stringArrayType, stringArrayList)
                                        stringArrayType = ""
                                        stringArrayList = null
                                    }
                                    result.add(item)
                                }
                            }
                        }
                    }
                    eventType = parser.next()
                }
            } catch (e: Exception) {
                return null
            }
            return result
        }
    }
}