package com.freedom.notey.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.freedom.notey.databinding.NoteLayoutBinding
import com.freedom.notey.db.Note
 var listener: NoteListener?=null
class NoteAdapter(val clickLitener: NoteClickLitener) :ListAdapter<Note,ViewHolder>(NoteAdapterDiffUtils())
{


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=getItem(position)
        holder.bind(item,clickLitener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun submitList(list: MutableList<Note>?) {
        super.submitList(list)
        if (list?.size==0){
            listener?.Success("empty")
            Log.d("any"," -----------------list count ${list.size}")
        }
    }

}

class ViewHolder constructor(val binding: NoteLayoutBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(item:Note,clickLitener: NoteClickLitener){
        binding.noteDB=item
        binding.clicklistener=clickLitener
        binding.executePendingBindings()
    }

    companion object{
        fun from(parent: ViewGroup):ViewHolder{
            val layoutInflater=LayoutInflater.from(parent.context)
            val binding=NoteLayoutBinding.inflate(layoutInflater,parent,false)
            return ViewHolder(binding)
        }
    }
}



class NoteAdapterDiffUtils :DiffUtil.ItemCallback<Note>(){

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return  oldItem==newItem
    }



}


class NoteClickLitener(val clicklistener: (Note)->Unit){
    fun onClick(Note:Note)=clicklistener(Note)
}

