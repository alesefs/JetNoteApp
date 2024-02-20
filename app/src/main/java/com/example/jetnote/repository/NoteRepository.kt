package com.example.jetnote.repository

import com.example.jetnote.data.NoteDatabaseDAO
import com.example.jetnote.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDatabaseDAO: NoteDatabaseDAO
){
    //suspend serve como semaforo na rodovia
    suspend fun addNote(note: Note) = noteDatabaseDAO.insert(note)
    suspend fun updateNote(note: Note) = noteDatabaseDAO.update(note)
    suspend fun deleteNote(note: Note) = noteDatabaseDAO.deleteNote(note)
    suspend fun deleteAllNotes() = noteDatabaseDAO.deleteAll()
    fun getAllNotes(): Flow<List<Note>> = noteDatabaseDAO.getNotes()
        .flowOn(Dispatchers.IO)
        .conflate()
}