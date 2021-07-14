package com.firstapp.ui.home.bookmarked

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firstapp.R
import com.firstapp.databinding.ItemBookmarkedBinding
import com.firstapp.db.entities.ArticleEntity
import com.firstapp.util.ItemClickListener

class  BookMarkAdapter(val articleEntityList: List<ArticleEntity> ,val clickListener: ItemClickListener?):
    RecyclerView.Adapter<BookMarkAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =ItemBookmarkedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNewsName.text =articleEntityList[position].title
        holder.binding.tvtime.text =articleEntityList[position].publishedAt
        if(!articleEntityList[position].urlToImage.isNullOrEmpty()){
            Glide.with(holder.binding.ivNews.context)
                .load(articleEntityList[position].urlToImage).into(holder.binding.ivNews)

        }else{
            Glide.with(holder.binding.ivNews.context)
                .load(R.drawable.img_girlres_background).into(holder.binding.ivNews)

        }
        holder.binding.ivDelete.setOnClickListener {
    clickListener?.onViewClicked(it,position)
        }
    }

    override fun getItemCount(): Int {
        return articleEntityList.size
    }

    class ViewHolder(val binding:ItemBookmarkedBinding) :RecyclerView.ViewHolder(binding.root)

}