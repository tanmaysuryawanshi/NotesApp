package com.tanmaysuryawanshi.notesappcompose.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.tanmaysuryawanshi.notesappcompose.model.Note

class NotesDataSource {
    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNotes(): List<Note> {
        return listOf(

            Note(title = "A good day", description = "We went on a vacation by the lake"),
            Note(title = "Android Compose", description = "Working on Android Compose course today"),
            Note(title = "Keep at it...", description = "Sometimes things just happen"),
            Note(title = "A movie day", description = "Watching a movie with family today"),
            Note(title = "A movie day", description = "Watching a movie with family today"),
            Note(title = "A movie day", description = "Watching a movie with family today"),
            Note(title = "A movie day", description = "Watching a movie with family today"),
            Note(title = "A movie day", description = "Watching a movie with family today"),
            Note(title = "A movie day", description = "Watching a movie with family today"),
            Note(title = "A movie day", description = "Watching a movie with family")

        )
    }
}