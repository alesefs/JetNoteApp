package com.example.jetnote.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.model.Note
import com.example.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    val repository: NoteRepository
) : ViewModel() {

//    var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
//        noteList.addAll(NotesDataSource().loadNotes()) //lista mockada
        //serve como uma rodovia com muitas faixas de transito, logo varios carros podem passar em paralelo
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect { listNotes ->
                    if (listNotes.isEmpty()) {
                        Log.d("ALELOG", ": Empty List")
                    } else {
                        _noteList.value = listNotes
                    }
                }
        }
    }

    /*fun addNote(note: Note) {
        noteList.add(note)
    }*/
    fun addNote(note: Note) = viewModelScope.launch {
        repository.addNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    /*fun removeNote(note: Note) {
        noteList.remove(note)
    }*/
    fun removeNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    /*fun getAllNotes(): List<Note> {
        return noteList
    }*/

}