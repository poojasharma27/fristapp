package com.firstapp.ui.home.bookmarked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firstapp.base.BaseActivity
import com.firstapp.databinding.FragmnetBookmarkedBinding
import com.firstapp.db.AppDataBase
import com.firstapp.network.model.Article
import com.firstapp.util.ItemClickListener
import com.firstapp.util.toArticle
import com.firstapp.util.toArticleEntity
import kotlinx.coroutines.launch


class BookMarkFragment : BaseActivity(), ItemClickListener {
    private var binding: FragmnetBookmarkedBinding? = null
    lateinit var articleList: List<Article>
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
        binding?.rVSecondVertical?.apply {
            this.adapter = BookMarkAdapter(articleList, this@BookMarkFragment)
        }
    }

    private fun getBookMarks() {
        launch {
            context?.let {
                articleList = AppDataBase.invoke(it).userDetailsDao().getArticleEntity().map {
                    it.toArticle()
                }.toList()
            }
        }
    }

    override fun onViewClicked(view: View, position: Int) {
        launch {
            context?.let {
                val item = articleList[position]
                (articleList as MutableList).remove(item)
                AppDataBase.invoke(it).userDetailsDao().deleteArticle(item.toArticleEntity())
                binding?.rVSecondVertical?.adapter?.notifyDataSetChanged()

            }
        }
    }

    override fun OnSaveClicked(view: View, article: Article) {
        TODO("Not yet implemented")
    }
}