package com.freedom.notey.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.datasource.emptyDataSource
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.swipe.SwipeLocation.RIGHT
import com.afollestad.recyclical.swipe.SwipeLocation.LEFT
import com.afollestad.recyclical.swipe.withSwipeActionOn
import com.afollestad.recyclical.withItem
import com.freedom.notey.R
import com.freedom.notey.db.Note
import com.freedom.notey.utils.NoteViewHolder
import com.freedom.notey.utils.snackbar
import com.freedom.notey.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.fragment_home.recy as list

class HomeFragment : Fragment() {

    private val noteViewModel:NoteViewModel by viewModel()
    lateinit var  viewModel:NoteViewModel
    lateinit var recyclerView: RecyclerView
    private var dataSource= emptyDataSource()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(noteViewModel::class.java)
        subscribeObserver()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        recyclerView= list
        btn_add.setOnClickListener{
            val action=HomeFragmentDirections.actionHomeFragmentToAddNote()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun subscribeObserver(){
        viewModel.loadData().observe(this, Observer {
            dataSource.add(it)
            dataSource = dataSourceTypedOf(it)
            setupRecyclerView()

        })

    }

    private fun setupRecyclerView(){
        recyclerView.setup {
            withLayoutManager(StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL))
            withEmptyView(emptystate)
            withDataSource(dataSource)
            withItem<Note,NoteViewHolder>(R.layout.note_layout){
                onBind(::NoteViewHolder){ _, item->
                    title.text=item.Title
                    note.text=item.Note
                }
                onClick { index ->
                    CoroutineScope(Main).launch {
                        val action=HomeFragmentDirections.actionHomeFragmentToAddNote()
                        action.note=viewModel.noteDao.getAllNote()[index]
                        findNavController().navigate(action)
                    }

                }
            }

            withSwipeActionOn<Note>(LEFT,RIGHT){
                color(R.color.white)
                icon(R.drawable.line)
                callback { _, item ->
                    viewModel.Delete(item)
                    root.snackbar("${item.Title} Deleted")
                    true
                }
            }
        }
    }


}

