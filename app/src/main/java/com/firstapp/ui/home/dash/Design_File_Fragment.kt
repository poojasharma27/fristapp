package com.firstapp.ui.home.dash

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.R


class Design_File_Fragment : Fragment() {
    lateinit var rvAllFile: RecyclerView
    lateinit var allFileAdapter: AllFileAdapter
    lateinit var  toolbar: LinearLayout
    val arrayList = arrayListOf("App Design", "Tour picture", "Working Website", "Client Documents")
    lateinit var allFileArrayList: ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_design_file, container, false)
        rvAllFile = v.findViewById(R.id.rvAllFill)
        allFileList()
        toolbar= v.findViewById(R.id.toolbar)
        toolbar.setBackgroundResource(R.color.lightBlue);
        val tvAppBarName: TextView =v.findViewById(R.id.tvAppBarName)
        tvAppBarName.setTextColor(Color.parseColor("#000000"))
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(rvAllFile)
        return v.rootView
    }

    private fun allFileList() {
        allFileAdapter = AllFileAdapter(
            arrayList,
            this
        )
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rvAllFile.layoutManager = linearLayoutManager
        rvAllFile.setAdapter(allFileAdapter)

    }

    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            Toast.makeText(activity, "on Move", Toast.LENGTH_SHORT).show()
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            Toast.makeText(activity, "on Swiped ", Toast.LENGTH_SHORT).show()
            //Remove swiped item from list and notify the RecyclerView
            val position = viewHolder.adapterPosition
            arrayList.removeAt(position)
            allFileAdapter.notifyDataSetChanged()
        }
    }
}