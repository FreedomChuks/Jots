package com.freedom.notey.ui


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.freedom.notey.R
import com.freedom.notey.databinding.FragmentAddNoteBinding
import com.freedom.notey.utils.NoteListener
import com.freedom.notey.utils.toast
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNoteFragment : Fragment(),NoteListener {

    lateinit var binding:FragmentAddNoteBinding

    val NoteViewModel:NoteViewModel by viewModel()

    lateinit var viewmodel:NoteViewModel

    override fun Success() {
        context?.toast("saved sucessfull")
    }

    override fun error(message: String) {
        context?.toast(message)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_add_note,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel= ViewModelProvider(this).get(NoteViewModel::class.java)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.noteViewModel=viewmodel
        viewmodel.noteListener=this
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
            R.id.save->{viewmodel.saveNotee()}
        }
        return super.onOptionsItemSelected(item)
    }

}

