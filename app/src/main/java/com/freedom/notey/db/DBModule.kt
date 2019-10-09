package com.freedom.notey.db

import androidx.room.Room
import com.freedom.notey.ui.NoteViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val persistentModule= module {

    single {

        Room.databaseBuilder(androidApplication(),NoteDatabase::class.java,"note")
            .fallbackToDestructiveMigration() // get correct db version if schema changed
            .build()
    }

    single { get<NoteDatabase>().getNoteDao }

    viewModel { NoteViewModel(get())}
}


