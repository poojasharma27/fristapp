package com.firstapp.ui.home.bookmarked

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firstapp.base.BaseActivity
import com.firstapp.databinding.FragmnetBookmarkedBinding
import com.firstapp.db.AppDataBase
import com.firstapp.db.entities.ArticleEntity
import com.firstapp.network.model.Article
import com.firstapp.util.ItemClickListener
import kotlinx.coroutines.launch


class BookMarkFragment : BaseActivity(),ItemClickListener {
    private var binding:FragmnetBookmarkedBinding?=null
   lateinit var articleEntity : List<ArticleEntity>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding= FragmnetBookmarkedBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch {
            context?.let {
              articleEntity=   AppDataBase.invoke(it).userDetailsDao().getArticleEntity()
                Log.d("articleEntity",articleEntity.toString())
                binding?.rVSecondVertical?.apply {
                    this.adapter=BookMarkAdapter(articleEntity,this@BookMarkFragment)
                }
            }
        }
    }

    override fun onViewClicked(view: View, position: Int) {
        launch {
            context?.let {
                val item = articleEntity[position]
                (articleEntity as MutableList).remove(item)
                AppDataBase.invoke(it).userDetailsDao().deleteArticle(item)
                binding?.rVSecondVertical?.adapter?.notifyDataSetChanged()

            }
        }
    }

    override fun OnSaveClicked(view: View, article: Article) {
        TODO("Not yet implemented")
    }
}