package com.tanmaysuryawanshi.notesappcompose.Screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanmaysuryawanshi.notesappcompose.data.NotesDataSource
import com.tanmaysuryawanshi.notesappcompose.model.Note
import com.tanmaysuryawanshi.notesappcompose.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository):ViewModel() {
    private var _noteList= MutableStateFlow<List<Note>>(emptyList())
    val noteList=_noteList.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect{
                  if(it.isNullOrEmpty()) {

                  }
                    else{
                        _noteList.value=it
                    }
                }
        }

//        noteList.addAll(NotesDataSource().loadNotes())
    }

fun addNote(note: Note)=viewModelScope.launch { repository.addNote(note) }
fun removeNote(note: Note)=viewModelScope.launch { repository.deleteNote(note)}
fun updateNote(note: Note)=viewModelScope.launch { repository.updateNote(note)}


}