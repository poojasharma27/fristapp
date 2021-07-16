package com.firstapp.ui.home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firstapp.R
import com.firstapp.databinding.ItemAddNewsRowBinding
import com.firstapp.db.entities.ArticleEntity

class NewsEntityAdapter(val list:List<ArticleEntity>) : RecyclerView.Adapter<NewsEntityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ItemAddNewsRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNewsName.text = list[position].title
        holder.binding.tvtime.text = list[position].publishedAt

        if(!(list[position].urlToImage.isNullOrEmpty())){
            Glide.with(holder.binding.ivNews.context)
                .load(list[position].urlToImage).into(holder.binding.ivNews)
        }else{
            Glide.with(holder.binding.ivNews.context)
                .load(R.drawable.img_girlres_background).into(holder.binding.ivNews)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }



    class ViewHolder(val binding: ItemAddNewsRowBinding) :RecyclerView.ViewHolder(binding.root)



}