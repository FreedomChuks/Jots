package com.freedom.notey.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class],version = 2, exportSchema = false)
abstract class NoteDatabase :RoomDatabase(){

    abstract val getNoteDao:NoteDao


}