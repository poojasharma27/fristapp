package com.firstapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.databinding.ItemSharedWithBinding
import com.firstapp.util.ItemClickListener

class SharedWithAdapter(private  var mData:List<String>,   val context: Context?, private val clickListener: ItemClickListener?):
    RecyclerView.Adapter<SharedWithAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =ItemSharedWithBinding.inflate( LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvDesign.text =mData[position]
        holder.binding.rlMain.setOnClickListener {
            clickListener?.onViewClicked(it, position)
        }

    }

    class ViewHolder(val binding :  ItemSharedWithBinding):RecyclerView.ViewHolder(binding.root)





}