package com.firstapp.ui.home.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firstapp.R
import com.firstapp.databinding.ItemRecommendedBinding
import com.firstapp.network.model.Article
import com.firstapp.util.ItemClickListener

class RecommendedAdapter(val list: List<Article>, private val clickListener: ItemClickListener?) :
    RecyclerView.Adapter<RecommendedAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ItemRecommendedBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = list[position]

        holder.binding.tvNewsName.text =list[position].title
        holder.binding.tvtime.text =list[position].publishedAt


        if(!list[position].urlToImage.isNullOrEmpty()){

                Glide.with(holder.binding.ivNews.context)
                    .load(list[position].urlToImage).into(holder.binding.ivNews)

        }else{


                Glide.with(holder.binding.ivNews.context)
                    .load(R.drawable.img_girlres_background).into(holder.binding.ivNews)

        }

        holder.binding.cLMain.setOnClickListener {
            clickListener?.onViewClicked(it, position)
        }
        holder.binding.ivbookmarked.setOnClickListener {
            clickListener?.OnSaveClicked(it,article)

        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }



    class ViewHolder(var binding:ItemRecommendedBinding) :RecyclerView.ViewHolder(binding.root)





}
