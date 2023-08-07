package com.tanmaysuryawanshi.notesappcompose.di

import android.content.Context
import androidx.room.Room
import com.tanmaysuryawanshi.notesappcompose.data.NoteDatabase
import com.tanmaysuryawanshi.notesappcompose.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
@Singleton
@Provides
fun provideNoteDao(noteDatabase:NoteDatabase):NoteDatabaseDao
=noteDatabase.noteDow()

    @Singleton
    @Provides
    fun provideApplicationDatabase(@ApplicationContext context: Context):NoteDatabase
    =Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "notes_db")
        .fallbackToDestructiveMigration()
        .build()


}
