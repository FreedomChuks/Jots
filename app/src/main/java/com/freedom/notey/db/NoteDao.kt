package com.freedom.notey.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert
    suspend fun saveNote(note: Note)

    @Query("Select * from note ORDER BY Id desc ")
    suspend fun getAllNote():List<Note>

    @Insert
    suspend fun saveAll(vararg note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Delete From note")
    suspend fun DeleteAll()
}