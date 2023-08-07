package com.tanmaysuryawanshi.notesappcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tanmaysuryawanshi.notesappcompose.model.Note
import com.tanmaysuryawanshi.notesappcompose.util.DateConverter
import com.tanmaysuryawanshi.notesappcompose.util.UUIDConverter


@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(UUIDConverter::class,DateConverter::class)
abstract class NoteDatabase:RoomDatabase() {

    abstract fun noteDow():NoteDatabaseDao

}