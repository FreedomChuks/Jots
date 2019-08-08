package com.freedom.notey.utils

import com.freedom.notey.db.Note

interface NoteListener {
    fun error(message:String)
    fun Success(note:Note)
}