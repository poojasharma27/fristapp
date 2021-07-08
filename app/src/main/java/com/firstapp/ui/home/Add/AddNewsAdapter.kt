package com.firstapp.ui.home.Add

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firstapp.R
import com.firstapp.model.Article

class AddNewsAdapter (val  list:List<Article>,val  addNewsFragment: AddNewsFragment) :
    RecyclerView.Adapter<AddNewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_add_news_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNewsName.text =list.get(position).title
        holder.tvtime.text =list.get(position).publishedAt

        /**
         * We can also use glide's placeholder
         */
        if(list.get(position).urlToImage.isNotEmpty()){
            Glide.with(addNewsFragment)
                .load(list.get(position).urlToImage).into(holder.ivNews)
        }else{

            Glide.with(addNewsFragment)
                .load(R.drawable.img_girlres_background).into(holder.ivNews)
        }

    }

    override fun getItemCount(): Int {
        return  list.size
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var tvNewsName: TextView = itemView.findViewById(R.id.tvNewsName)
        var tvtime: TextView = itemView.findViewById(R.id.tvtime)
        var ivNews: ImageView = itemView.findViewById(R.id.ivNews)

    }

}