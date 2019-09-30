package com.freedom.notey.BindingUtils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.freedom.notey.db.Note


@BindingAdapter("Notetext")
fun TextView.setNote(item:Note){
    text=item.Note
}

@BindingAdapter("Titletext")
fun TextView.setTitle(item:Note){
    text=item.Title
}