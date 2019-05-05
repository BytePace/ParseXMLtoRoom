package com.bytepace.ipr.utils

import java.security.MessageDigest

class HashUtil {
    companion object {
        fun sha512(input: String) = hashString("SHA-512", input)

        fun sha256(input: String) = hashString("SHA-256", input)

        fun sha1(input: String) = hashString("SHA-1", input)

        fun md5(input: String) = hashString("MD5", input)

        private fun hashString(type: String, input: String): String {
            return MessageDigest
                .getInstance(type)
                .digest(input.toByteArray())
                .map { String.format("%02X", it) }
                .joinToString(separator = "").toLowerCase()
        }
    }
}