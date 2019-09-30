package com.freedom.notey.ui.main


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.freedom.notey.R
import com.freedom.notey.db.Note
import com.freedom.notey.ui.NoteViewModel
import com.freedom.notey.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : MainNavigationFragment() {

    private val noteViewModel: NoteViewModel by viewModel()
    private lateinit var  viewModel: NoteViewModel
    private lateinit var adapter:NoteAdapter
    private lateinit var list :RecyclerView
    private var lists: ArrayList<Note> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View =inflater.inflate(R.layout.fragment_home,container,false)
        viewModel=ViewModelProvider(this).get(noteViewModel::class.java)
        setupViews(view)
        setupRecylerView()
        subscribeObserver()
        return view
    }


    private fun subscribeObserver(){
        viewModel.loadData().observe(viewLifecycleOwner, Observer {
            lists.addAll(it)
            val newlist=ArrayList<Note>()
            newlist.addAll(it)
            adapter.submitList(newlist)

        })
    }

    private fun setupRecylerView(){

        adapter= NoteAdapter(NoteClickLitener {
            val action=HomeFragmentDirections.actionHomeFragmentToAddNote()
            action.note=it
            findNavController().navigate(action)
        })
        list.apply {
            layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            adapter=this@HomeFragment.adapter
        }

        ItemTouchHelper(object : SimpleCallback(
            0,
            LEFT or RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val note=adapter.currentList[viewHolder.adapterPosition]
                val position=viewHolder.layoutPosition
                lists.removeAt(position)
                viewModel.Delete(note)
                val newlist=ArrayList<Note>()
                newlist.addAll(lists)
                adapter.submitList(newlist)
                root.snack("Moved To Trash"){
                    action("Undo"){
                        viewModel.insertNote(note)
                        lists.add(position,note)
                        val newlists=ArrayList<Note>()
                        newlists.addAll(lists)
                        adapter.submitList(newlists)

                    }
                }
            }
        }).attachToRecyclerView(list)
    }

    private fun setupViews(view: View){
        setHasOptionsMenu(true)
        list=view.findViewById(R.id.recyclerView)
        val btnAdd=view.findViewById<Button>(R.id.btn_add)
        val toolbar=view.findViewById<Toolbar>(R.id.toolbar)

        btnAdd.setOnClickListener {
            val action= HomeFragmentDirections.actionHomeFragmentToAddNote()
            Navigation.findNavController(it).navigate(action)
        }

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { (activity as AppCompatActivity).drawerlayout.openDrawer(
            GravityCompat.START) }

        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.changeview -> {
                if (list.layoutManager is LinearLayoutManager){

                    item.icon=resources.getDrawable(R.drawable.ic_outline_view,null)
                    list.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

                }else{
                    item.icon=resources.getDrawable(R.drawable.ic_grid,null)
                    list.layoutManager=LinearLayoutManager(context)
                }


            }
        }
        return super.onOptionsItemSelected(item)
    }


}










