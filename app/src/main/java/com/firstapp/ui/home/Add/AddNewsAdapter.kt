package com.firstapp.ui.home.Add

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firstapp.R
import com.firstapp.network.model.Article

class AddNewsAdapter (val  list:List<Article>, val  context: Context?) :
    RecyclerView.Adapter<AddNewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_add_news_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNewsName.text = list[position].title
        holder.tvtime.text = list[position].publishedAt

        /**
         * We can also use glide's placeholder
         */

        context?.let {
            if(!(list[position].urlToImage.isNullOrEmpty())){
                Glide.with(context)
                    .load(list[position].urlToImage).into(holder.ivNews)
            }else{
                Glide.with(context)
                    .load(R.drawable.img_girlres_background).into(holder.ivNews)
            }
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