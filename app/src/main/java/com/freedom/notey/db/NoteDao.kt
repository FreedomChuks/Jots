package com.freedom.notey.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert
    suspend fun saveNote(note: Note)

    @Query("Select * from note")
    suspend fun getAllNote():List<Note>
}