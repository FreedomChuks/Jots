package com.freedom.notey.di

import android.app.Application
import androidx.room.Room
import com.freedom.notey.db.NoteDatabase
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @JvmStatic
    @Provides
    fun provideNoteDataBase(app:Application):NoteDatabase
    {
        return Room.databaseBuilder(app,NoteDatabase::class.java,"note")
        .fallbackToDestructiveMigration() // get correct db version if schema changed
        .build()
    }

}