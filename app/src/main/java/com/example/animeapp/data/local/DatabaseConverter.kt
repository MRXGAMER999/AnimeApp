package com.example.animeapp.data.local

import androidx.room.TypeConverter

class DatabaseConverter {
    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for(items in list){
            stringBuilder.append(items).append(separator)
        }
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String>{
        return string.split(separator)
    }
}