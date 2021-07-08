package com.firstapp.ui.home.dash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.R

class AllFileAdapter(private var lData: ArrayList<String>, private var desginFragment: Design_File_Fragment) :
    RecyclerView.Adapter<AllFileAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.item_all_file,parent,false)
        return AllFileAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return lData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvAllName.text=lData.get(position)
    }
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
      val tvAllName: TextView =itemView.findViewById(R.id.tvAppName)
    }
}