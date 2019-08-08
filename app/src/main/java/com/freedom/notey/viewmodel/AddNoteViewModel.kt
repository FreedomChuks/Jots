package com.freedom.notey.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freedom.notey.db.Note
import com.freedom.notey.db.NoteDatabase
import com.freedom.notey.utils.NoteListener
import kotlinx.coroutines.launch

class AddNoteViewModel :ViewModel(){
    var title:String?=null
    var note:String?=null
    var noteListener: NoteListener?=null

    fun saveNotee(){
        if (title.isNullOrEmpty()){
            noteListener?.error("cant save empty text")
            return
        }else if(note.isNullOrEmpty()){
            noteListener?.error("cant save empty text")
            return
        }
        val note=Note(title!!,note!!)
        noteListener?.Success(note)
    }


}