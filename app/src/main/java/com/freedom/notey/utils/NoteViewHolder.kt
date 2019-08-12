package com.freedom.notey.utils

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.freedom.notey.R


class NoteViewHolder (itemView:View): RecyclerView.ViewHolder(itemView) {
    val title:TextView=itemView.findViewById(R.id.titles)
    val note:TextView=itemView.findViewById(R.id.note)
}