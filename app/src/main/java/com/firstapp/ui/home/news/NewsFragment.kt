package com.firstapp.ui.home.news

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firstapp.R
import com.firstapp.base.BaseActivity
import com.firstapp.databinding.FragmentAddNewsBinding
import com.firstapp.db.AppDataBase
import com.firstapp.db.entities.ArticleEntity
import com.firstapp.network.ApiServiceIn
import com.firstapp.network.ApiServices
import com.firstapp.network.model.Article
import com.firstapp.network.model.NewsResponse
import com.firstapp.ui.home.bookmarked.BookMarkAdapter
import com.firstapp.util.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : BaseActivity(),ItemClickListener {
    private var binding: FragmentAddNewsBinding? = null
    private var addNewsAdapter: AddNewsAdapter? = null
    private var recommendedAdapter: RecommendedAdapter? = null
    private var list: ArrayList<Article> = ArrayList()
    private var listArticleEntity: ArrayList<ArticleEntity> = ArrayList<ArticleEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNewsBinding.inflate(inflater, container, false)
        val view = binding?.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup  news recyclerView

        setupRecyclerViews()
        //making the news api calls
        getNews()

    }

    private fun setupRecyclerViews() {
        if(isInternetAvailable(activity as? Context)) {

        binding?.rVMainHorizontial?.apply {
            this.adapter = AddNewsAdapter(list)
        }

            binding?.rVSecondVertical?.apply {
                this.adapter = RecommendedAdapter(list, activity, object : ItemClickListener {
                    override fun onViewClicked(view: View, position: Int) {
                        val newsDescriptionFragment = NewsDescriptionFragment()
                        val fragmentManager = activity?.supportFragmentManager
                        val fragmentTransaction = fragmentManager?.beginTransaction()
                        val bundle = Bundle()
                        bundle.putParcelable(ExtrasConstants.Users.name, list[position])
                        newsDescriptionFragment.arguments = bundle
                        fragmentTransaction?.replace(R.id.flMain, newsDescriptionFragment)
                        fragmentTransaction?.addToBackStack("null")
                        fragmentTransaction?.commit()
                    }

                    override fun OnSaveClicked(view: View, article: Article) {
                       /* val articleEntity = ArticleEntity(
                            author = article.author,
                            content = article.content,
                            description = article.description,
                            publishedAt = article.publishedAt,
                            title = article.title,
                            url = article.url,
                            urlToImage = article.urlToImage
                        )

                        launch {
                            context?.let {
                                val articleEntity = AppDataBase.invoke(it).userDetailsDao()
                                    .addArticle(articleEntity)
                                Log.d("articleEntity", articleEntity.toString())

                            }
                        }*/
                    }

                })

            }
           /* listArticleEntity.map {
                ArticleEntity(it.author,it.content,it.description,it.publishedAt,it.title,it.url,it.urlToImage)*/

        }else{
            launch {
                context?.let {
                  val  articleEntity=   AppDataBase.invoke(it).userDetailsDao().getArticleEntity()
                    Log.d("articleEntity",articleEntity.toString())
                    binding?.rVSecondVertical?.apply {
                        this.adapter= BookMarkAdapter(articleEntity,this@NewsFragment)
                    }
                    binding?.rVMainHorizontial?.apply {
                        this.adapter = NewsEntityAdapter(articleEntity)

                    }

                }
            }
        }


    }

    private fun getNews() {
        val apiServiceIn =
            ApiServices.getRetrofitInstance().create(ApiServiceIn::class.java)
        val call: Call<NewsResponse> =
            apiServiceIn.news(
                "tesla",
                2021 - 6 - 7,
                "publishedAt",
                "ef10af13e36045b4a965734696d81186",10
            )
        call.enqueue(object : Callback<NewsResponse> {


            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {

                    response.body()?.let {
                        setNewsList(it.articles)
                        setRecommendList(it.articles)
                    }

                    Log.d("moshi", "success")

                }
            }


            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                showToastLong(activity, "error")
                Log.d("moshi", "error")
            }


        })
    }

    private fun setNewsList(list: List<Article>) {
        this.list.addAll(list)
        launch {
            val articleEntityList = mutableListOf<ArticleEntity>()
            for(item in list){
                articleEntityList.add(item.toArticleEntity())
            }
            context?.let {
                val articleEntity = AppDataBase.invoke(it).userDetailsDao()
                    .addArticle(articleEntityList)

                Log.d("articleEntity", articleEntity.toString())

            }
        }
        binding?.rVMainHorizontial?.adapter?.notifyDataSetChanged()
    }


    private fun setRecommendList(list: List<Article>) {
        this.list.addAll(list)
        launch {
            val articleEntityList = mutableListOf<ArticleEntity>()
            for(item in list){
                articleEntityList.add(item.toArticleEntity())
            }
            context?.let {
                val articleEntity = AppDataBase.invoke(it).userDetailsDao()
                    .addArticle(articleEntityList)

                Log.d("articleEntity", articleEntity.toString())

            }
        }
        binding?.rVSecondVertical?.adapter ?.notifyDataSetChanged()
    }

    override fun onViewClicked(view: View, position: Int) {
        TODO("Not yet implemented")
    }

    override fun OnSaveClicked(view: View, article: Article) {
        TODO("Not yet implemented")
    }

    /* override fun onViewClicked(view: View, position: Int) {
         val newsDescriptionFragment = NewsDescriptionFragment()
         val fragmentManager = activity?.supportFragmentManager
         val fragmentTransaction = fragmentManager?.beginTransaction()
         val bundle = Bundle()
         bundle.putParcelable(ExtrasConstants.Users.name, list[position])
         newsDescriptionFragment.arguments = bundle
         fragmentTransaction?.replace(R.id.flMain, newsDescriptionFragment)
         fragmentTransaction?.addToBackStack("null")
         fragmentTransaction?.commit()
     }

     override fun OnSaveClicked(view: View, article: Article) {
         val articleEntity = ArticleEntity(
             author = article.author,
             content = article.content,
             description = article.description,
             publishedAt = article.publishedAt,
             title = article.title,
             url = article.url,
             urlToImage = article.urlToImage
         )
         launch {
             context?.let {
               val articleEntity= AppDataBase.invoke(it).userDetailsDao().addArticle(articleEntity)
                 Log.d("articleEntity",articleEntity.toString())

             }
         }


     }*/
  /* fun isInternetAvailable(): Boolean {
       val connectivityManager =activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
       val activeNetwork = connectivityManager.activeNetwork
       connectivityManager.getNetworkCapabilities(activeNetwork)?.run {
           when {
               hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
               hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
               hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
               else -> false
           }
       }
       return false
   }*/

}


