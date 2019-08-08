package com.freedom.notey.ui


import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.freedom.notey.R
import com.freedom.notey.databinding.FragmentAddNoteBinding
import com.freedom.notey.db.Note
import com.freedom.notey.db.NoteDatabase
import com.freedom.notey.utils.NoteListener
import com.freedom.notey.utils.toast
import com.freedom.notey.viewmodel.AddNoteViewModel
import kotlinx.android.synthetic.main.toolbar.*

class AddNote : Fragment(),NoteListener {

    lateinit var binding:FragmentAddNoteBinding
    lateinit var viewModel: AddNoteViewModel

    override fun Success(note: Note) {

            class SaveData: AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg p0: Void?): Void? {
//                    NoteDatabase(activity!!).getNoteDao.saveNote(note)
                    return null
                }

                override fun onPostExecute(result: Void?) {
                    context?.toast("saved successfully")
                }
            }
        SaveData().execute()
    }

    override fun error(message: String) {
        context?.toast(message)
    }


    //Todo init view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_add_note,container,false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(AddNoteViewModel::class.java)
        binding.noteViewModel=viewModel
        viewModel.noteListener=this

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val action=(activity as AppCompatActivity).supportActionBar
        action?.setDisplayHomeAsUpEnabled(true)
        action?.setDisplayShowTitleEnabled(false)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_note_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.save->{viewModel.saveNotee()}
        }
        return super.onOptionsItemSelected(item)
    }

}

