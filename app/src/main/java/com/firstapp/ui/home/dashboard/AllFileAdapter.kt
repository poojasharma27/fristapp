package com.firstapp.ui.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.databinding.ItemAllFileBinding

class AllFileAdapter(private var lData: ArrayList<String>,  val context: Context?) :
    RecyclerView.Adapter<AllFileAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAllFileBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return lData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvAppName.text=lData[position]
    }
    class ViewHolder(val binding: ItemAllFileBinding) : RecyclerView.ViewHolder(binding.root)
}