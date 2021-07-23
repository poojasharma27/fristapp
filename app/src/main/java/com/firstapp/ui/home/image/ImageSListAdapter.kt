package com.firstapp.ui.home.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firstapp.databinding.ItemImageBinding
import com.firstapp.db.entities.ImagesEntity

class ImageSListAdapter( private val imageList:ArrayList<ImagesEntity>):
    RecyclerView.Adapter<ImageSListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding=ItemImageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.binding.ivDb.context).load(imageList[position].images).into(holder.binding.ivDb)
        holder.binding.ivDb.setOnClickListener {
            removeList(position)

        }
    }

    private fun removeList(position: Int) {
        imageList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
    class ViewHolder(val binding:  ItemImageBinding) :RecyclerView.ViewHolder(binding.root)


}