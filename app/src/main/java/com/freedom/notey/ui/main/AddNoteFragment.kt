package com.freedom.notey.ui.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.freedom.notey.R
import com.freedom.notey.databinding.FragmentAddNoteBinding
import com.freedom.notey.db.Note
import com.freedom.notey.ui.NoteViewModel
import com.freedom.notey.ui.secureNote.PinDialog
import com.freedom.notey.utils.MainNavigationFragment
import com.freedom.notey.utils.NoteListener
import kotlinx.android.synthetic.main.fragment_add_note.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNoteFragment : MainNavigationFragment(), NoteListener {

    lateinit var binding: FragmentAddNoteBinding

    private val noteViewModel: NoteViewModel by viewModel()

    lateinit var viewmodel: NoteViewModel
    lateinit var pinDialog: PinDialog

    private var notes: Note? =null

    override fun Success(message: String) {
        findNavController().navigate(AddNoteFragmentDirections.actionAddNoteToHomeFragment())
    }

    override fun error(message: String) {
        findNavController().navigate(AddNoteFragmentDirections.actionAddNoteToHomeFragment())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        viewmodel = ViewModelProvider(this).get(noteViewModel::class.java)
        pinDialog=PinDialog()
        toolbar.title=""
        toolbar.setNavigationIcon(R.drawable.ic_arrow)
        toolbar.setNavigationOnClickListener { viewmodel.saveNotee() }
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.noteViewModel = viewmodel
        viewmodel.noteListener = this
        arguments?.let {
            notes= AddNoteFragmentArgs.fromBundle(it).note
            viewmodel.notes=this.notes
            viewmodel.title=notes?.Title
            viewmodel.note=notes?.Note
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.lock->{pinDialog.show(fragmentManager!!,"data")}
        }
        return super.onOptionsItemSelected(item)
    }







}

