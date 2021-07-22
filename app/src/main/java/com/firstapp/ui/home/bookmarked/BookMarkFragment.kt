package com.firstapp.ui.home.bookmarked

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firstapp.base.BaseFragment
import com.firstapp.databinding.FragmnetBookmarkedBinding
import com.firstapp.db.AppDataBase
import com.firstapp.network.model.Article
import com.firstapp.util.ItemClickListener
import com.firstapp.util.toArticle
import kotlinx.coroutines.launch


class BookMarkFragment : BaseFragment(), ItemClickListener {
    private var binding: FragmnetBookmarkedBinding? = null
     var articleList: ArrayList<Article> = ArrayList<Article>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmnetBookmarkedBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        //setting up recyclerView
        setUpBookmarksRecyclerView()
        
        //getting the bookmarks
        getBookMarks()
        
    }

    private fun setUpBookmarksRecyclerView() {

    }

    private fun getBookMarks() {
       /* launch {
            context?.let {
                articleList = AppDataBase.invoke(it).userDetailsDao().getArticleEntity().map {
                    it.toArticle()
                }.toList()
            }
        }*/
        launch {
            context?.let {
                val articleEntity = AppDataBase.invoke(it).userDetailsDao().getArticleEntity()
               //  articleList = ArrayList<Article>()
                Log.d("articleEntity", articleEntity.toString())
                articleList.addAll(articleEntity.map {
                    it.toArticle()
                })

            }
        }
        binding?.rVSecondVertical?.apply {
            this.adapter = BookMarkAdapter(articleList, this@BookMarkFragment)
        }
    }

    override fun onViewClicked(view: View, position: Int) {
        /*launch {
            context?.let {
                val item = articleList[position]
                (articleList as MutableList).remove(item)
                AppDataBase.invoke(it).userDetailsDao().deleteArticle(item.toArticleEntity())
                binding?.rVSecondVertical?.adapter?.notifyDataSetChanged()

            }
        }*/
    }

    override fun OnSaveClicked(view: View, article: Article) {
        TODO("Not yet implemented")
    }
}