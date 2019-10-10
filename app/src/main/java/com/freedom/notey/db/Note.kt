package com.freedom.notey.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDateTime


//Todo this is an abstraction layer to create sqlite table
//Todo name of the class is the same as the table
@Entity
data class Note(
    val Title:String?=null,//column 2
    val Note:String?=null// column 3
//    val modifiedAt:LocalDateTime,
//    val createdAt:LocalDateTime,
//    val pin:Int

):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}
