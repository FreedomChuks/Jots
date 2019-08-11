package com.freedom.notey.db

import androidx.room.Entity
import androidx.room.PrimaryKey

//Todo this is an abstraction layer to create sqlite table
//Todo name of the class is the same as the table
@Entity
data class Note(
    val Title:String="",//column 2
    val Note:String=""// column 3
){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}