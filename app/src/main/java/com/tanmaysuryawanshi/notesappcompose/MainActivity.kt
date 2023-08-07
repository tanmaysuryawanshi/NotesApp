package com.tanmaysuryawanshi.notesappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tanmaysuryawanshi.notesappcompose.Screen.NoteScreen
import com.tanmaysuryawanshi.notesappcompose.Screen.NoteViewModel
import com.tanmaysuryawanshi.notesappcompose.data.NotesDataSource
import com.tanmaysuryawanshi.notesappcompose.model.Note
import com.tanmaysuryawanshi.notesappcompose.ui.theme.NotesAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppComposeTheme {
val noteViewModel:NoteViewModel by viewModels()
             NoteApp(noteViewModel)

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesAppComposeTheme {
        Greeting("Android")
    }
}

@Composable
fun NoteApp(noteViewModel: NoteViewModel) {
    val noteList=noteViewModel.noteList.collectAsState().value
    NoteScreen(notes = noteList,
        onAddNotes = {
           noteViewModel.addNote(it)
        },
        onRemoveNotes = {
            noteViewModel.removeNote(it)
        })
}