package com.example.jetnote.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetnote.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDAO {

    /*
    SELECT = seleciona
    * = todos
    from = da tabela
    notes_tbl = nome da tabela
    */
    @Query("SELECT * from notes_tbl")
    fun getNotes(): Flow<List<Note>>

    /*
    SELECT = seleciona
    * = todos
    from = da tabela
    notes_tbl = nome da tabela
    where = no qual
    id = :id = id for = a id do Note.dataClass
    */
    @Query("SELECT * from notes_tbl where id = :id")
    suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)//se conflitar com uma antiga vai fazer a troca pela nova
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)//se conflitar com uma antiga vai fazer a troca pela nova
    suspend fun update(note: Note)

    @Query("DELETE from notes_tbl")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)

    //suspend suspende da main thread a função
}
