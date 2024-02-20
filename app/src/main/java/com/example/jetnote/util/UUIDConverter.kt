package com.example.jetnote.util

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {
    //serve para converter em dados conhecidos pelo Room
    @TypeConverter
    fun fromUUID(uuid: UUID): String? {
        return uuid.toString()
    }

    @TypeConverter
    fun uuidFromString(string: String?): UUID? {
        return UUID.fromString(string)
    }
}
