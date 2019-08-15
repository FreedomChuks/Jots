package com.freedom.notey.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.freedom.notey.db.Note
import com.freedom.notey.db.NoteDao
import com.freedom.notey.utils.NoteListener
import kotlinx.coroutines.launch

class NoteViewModel(val noteDao: NoteDao):ViewModel(){
    var title:String?=null
    var note:String?=null
    var id:Int?=null
    var noteListener: NoteListener?=null
    var notes: Note?=null

    fun saveNotee(){
        if (title.isNullOrEmpty()){
            noteListener?.error("cant save empty text")
            return
        }else if(note.isNullOrEmpty()){
            noteListener?.error("cant save empty text")
            return
        }
        val note= Note(title!!,note!!)
        if (notes==null){
            insertNote(note)
            noteListener?.Success("saved successful")
            return
        }
        note.id=notes!!.id
        updateNote(note)
        noteListener?.Success("note updated")

    }

    fun insertNote(note:Note)=viewModelScope.launch {
        noteDao.saveNote(note)
    }
    fun updateNote(note: Note)=viewModelScope.launch {
        noteDao.UpdateNote(note)
    }
    fun loadData()= liveData {
        emit(noteDao.getAllNote())
    }
    fun DeleteAll()=viewModelScope.launch{
        noteDao.DeleteAll()
    }

    fun Delete(note: Note)=viewModelScope.launch {
        noteDao.delete(note)
    }
}