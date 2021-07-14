package com.firstapp.ui.home.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.R
import com.firstapp.databinding.FragmentDesignFileBinding
import com.firstapp.util.showToastShort


class DesignFileFragment : Fragment() {
    private var binding: FragmentDesignFileBinding? = null
    lateinit var allFileAdapter: AllFileAdapter
     private var  toolbar: LinearLayout? = null
    val arrayList = arrayListOf("App Design", "Tour picture", "Working Website", "Client Documents")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDesignFileBinding.inflate(inflater, container, false)
        val view = binding?.root
        allFileList()
        toolbar= view?.findViewById(R.id.toolbar)
        toolbar?.setBackgroundResource(R.color.lightBlue)
        val tvAppBarName: TextView ?=view?.findViewById(R.id.tvAppBarName)
        tvAppBarName?.setTextColor(Color.parseColor("#000000"))
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding?.rvAllFill)
        return view
    }

    private fun allFileList() {
        allFileAdapter = AllFileAdapter(
            arrayList,
            activity
        )
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
       binding?.rvAllFill ?.layoutManager = linearLayoutManager
        binding?.rvAllFill ?.adapter=allFileAdapter

    }

  private  var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            context?.let {
                showToastShort(it,"on Move")
            }
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            context?.let {
                showToastShort(it,"on Swiped")
            }
            //Remove swiped item from list and notify the RecyclerView
            val position = viewHolder.bindingAdapterPosition
            arrayList.removeAt(position)
            allFileAdapter.notifyDataSetChanged()
        }
    }
}