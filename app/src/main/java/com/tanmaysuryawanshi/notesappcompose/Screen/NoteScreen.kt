package com.tanmaysuryawanshi.notesappcompose.Screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tanmaysuryawanshi.notesappcompose.R
import com.tanmaysuryawanshi.notesappcompose.components.NoteButton
import com.tanmaysuryawanshi.notesappcompose.components.NoteInputText
import com.tanmaysuryawanshi.notesappcompose.data.NotesDataSource
import com.tanmaysuryawanshi.notesappcompose.model.Note
import com.tanmaysuryawanshi.notesappcompose.util.formatDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes:List<Note> = NotesDataSource().loadNotes(),
    onAddNotes: (Note)->Unit={},
    onRemoveNotes: (Note)->Unit={}
    ) {


val context= LocalContext.current
    Column(modifier = Modifier.padding(8.dp)) {
        var title=remember{
            mutableStateOf("")
        }
        var description=remember{
            mutableStateOf("")
        }
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        actions = {
            Image(imageVector = Icons.Default.Notifications,
                contentDescription =null )
        })

        Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

            NoteInputText(text = title.value, label = "add title",
                onTextChange = {
        if (it.all {
                         char ->
                         char.isLetter() || char.isWhitespace()
                          })title.value=it
            },
                modifier = Modifier. padding(vertical = 8.dp))
            NoteInputText(text = description.value, label = "add description", onTextChange = {

                if (it.all {
                            char ->
                        char.isLetter() || char.isWhitespace()
                    })description.value=it

            },
                modifier = Modifier. padding(vertical = 8.dp))

            NoteButton(text = "Save", onClick = {

if(title.value.isNotEmpty() && description.value.isNotEmpty()){
onAddNotes(Note(title = title.value,
description = description.value))

    title.value=""
    description.value=""
    Toast.makeText(context,"Added",Toast.LENGTH_SHORT).show()

}

            },
            modifier = Modifier.padding(8.dp))
        }



        Divider(modifier = Modifier.padding(10.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()){

           items(items = notes){ it ->
               NoteRow( modifier = Modifier.fillMaxWidth(), note = it, onNoteClicked = {
                   onRemoveNotes(it)
               })
           }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteRow(
    modifier: Modifier=Modifier,
    note:Note,
    onNoteClicked:(Note) -> Unit
) {
    
    Card(shape = RoundedCornerShape(16.dp),
    modifier = Modifier.fillMaxWidth().padding(8.dp),
    elevation = CardDefaults.cardElevation(4.dp), onClick = {
     onNoteClicked(note)
        }
    ) {
Column(modifier = Modifier.padding(16.dp)) {

    Text(text = note.title,
    style = MaterialTheme.typography.titleMedium
    )
    Text(text = note.description,
        style = MaterialTheme.typography.bodyMedium
    )

    Text(text = formatDate(note.entryDate.time),
        style = MaterialTheme.typography.labelMedium
    )
}

    }

}