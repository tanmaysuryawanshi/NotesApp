package com.tanmaysuryawanshi.notesappcompose.util

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {

    @TypeConverter
    fun fromUUID(uuid: UUID):String?{
        return uuid.toString()
    }

    @TypeConverter
    fun UUIDfromString(string:  String):UUID?{
        return UUID.fromString(string)
    }
}