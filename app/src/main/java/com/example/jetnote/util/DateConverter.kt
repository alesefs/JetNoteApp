package com.example.jetnote.util

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    //serve para converter em dados conhecidos pelo Room
    @TypeConverter
    fun timeStampFromDate(date: Date): Long {
        return date.time

    }
    @TypeConverter
    fun dateFromTimestamp(timestamp: Long): Date? {
        return Date(timestamp)
    }
}
