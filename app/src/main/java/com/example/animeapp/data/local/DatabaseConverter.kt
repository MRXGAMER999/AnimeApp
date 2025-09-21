package com.example.animeapp.data.local

import androidx.room.TypeConverter

class DatabaseConverter {
    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        if (list.isEmpty()) return ""
        val stringBuilder = StringBuilder()
        for (items in list) {
            stringBuilder.append(items).append(separator)
        }
        // Safely trim the trailing separator only if present
        if (stringBuilder.endsWith(separator)) {
            stringBuilder.setLength(stringBuilder.length - separator.length)
        }
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String>{
        if (string.isEmpty()) return emptyList()
        return string.split(separator)
    }
}