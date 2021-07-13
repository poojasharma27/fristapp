package com.firstapp.ui.home.Add

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firstapp.R
import com.firstapp.databinding.ItemRecommendedBinding
import com.firstapp.network.model.Article
import com.firstapp.util.ItemClickListener

class RecommendedAdapter(val list: List<Article>, val context: Context?, private val clickListener: ItemClickListener?) :
    RecyclerView.Adapter<RecommendedAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ItemRecommendedBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNewsName.text =list[position].title
        holder.binding.tvtime.text =list[position].publishedAt


        if(!list[position].urlToImage.isNullOrEmpty()){
            context?.let {
                Glide.with(it)
                    .load(list[position].urlToImage).into(holder.binding.ivNews)
            }
        }else{

            context?.let {
                Glide.with(it)
                    .load(R.drawable.img_girlres_background).into(holder.binding.ivNews)
            }
        }
        holder.binding.cLMain.setOnClickListener {
            clickListener?.onViewClicked(it, position)
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }



    class ViewHolder(var binding:ItemRecommendedBinding) :RecyclerView.ViewHolder(binding.root)





}
