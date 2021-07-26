package com.firstapp.ui.home.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.R
import com.firstapp.base.BaseFragment
import com.firstapp.databinding.FragmentAddNewsBinding
import com.firstapp.db.AppDataBase
import com.firstapp.db.entities.ArticleEntity
import com.firstapp.network.ApiServiceIn
import com.firstapp.network.ApiServices
import com.firstapp.network.model.Article
import com.firstapp.network.model.NewsResponse
import com.firstapp.ui.home.DashboardActivity
import com.firstapp.util.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : BaseFragment(), ItemClickListener {
    private var binding: FragmentAddNewsBinding? = null
    private var list: ArrayList<Article> = ArrayList()
    private var isLoading: Boolean = true
    var visibleItem: Int? = null
    var scrollItem: Int? = null
    var totalItem: Int? = null
    lateinit var centralProgressBar: ProgressBar
    lateinit var bottomProgressBar: ProgressBar
    lateinit var topProgressBar: ProgressBar
    lateinit var progressBar: ProgressBar
    var pageCount = 1
   lateinit var fragmentClickListener: FragmentClickListener
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        //making the news api cal
        fragmentClickListener=activity as FragmentClickListener
      //  centralProgressBar = view.findViewById(R.id.centralPB)
        bottomProgressBar = view.findViewById(R.id.bottomPB)
      //  topProgressBar = view.findViewById(R.id.topProgressBar)

        // Add ProgressBar to our layout
       // progressBarDialog(context)
        /*progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleSmall);
        progressBar.isIndeterminate=true*/
        (activity as DashboardActivity).showProgressBar()
        getNews()
    }

    private fun setupRecyclerViews() {
        binding?.rVMainHorizontial?.apply {
            this.adapter = AddNewsAdapter(list)
        }

        binding?.rVSecondVertical?.apply {
            this.adapter = RecommendedAdapter(list, object : ItemClickListener {
                override fun onViewClicked(view: View, position: Int) {
                    openNewsDetail(position)
                }

                override fun OnSaveClicked(view: View, article: Article) {

                }
            })
        }

        binding?.rVSecondVertical?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy >= 0) {
                    visibleItem = binding?.rVSecondVertical?.layoutManager?.childCount
                    totalItem = binding?.rVSecondVertical?.layoutManager?.itemCount
                    scrollItem =
                        (binding?.rVSecondVertical?.layoutManager as? LinearLayoutManager)?.findFirstCompletelyVisibleItemPosition()
                    if (isLoading) {
                     binding?.bottomPB ?.visibility=View.VISIBLE
                        if (scrollItem?.let { visibleItem?.plus(it)!! >= totalItem!! } == true && scrollItem!! >= 0) {
                            isLoading = false
                            pageCount.inc()
                            getNews()
                            binding?.bottomPB ?.visibility=View.GONE
                            Log.d("page", pageCount.toString())

                        }
                    }
                }
            }

        })

    }

    private fun openNewsDetail(position: Int) {
        val newsDescriptionFragment = NewsDescriptionFragment()
        //setup frgament Data
        val bundle = Bundle()
        bundle.putParcelable(ExtrasConstants.Users.name, list[position])
        newsDescriptionFragment.arguments = bundle
         fragmentClickListener.onFragmentTransaction(newsDescriptionFragment)

    }


    private fun getNews() {
        if (isInternetAvailable(activity as? Context)) {
            broadcastNetworkStatus(connected)
            getNewsFromNetwork()
        } else {
           // showToastLong(activity, "Internet not available")
            broadcastNetworkStatus(disconnected)
            getNewsFromDb()
        }

    }

    private fun broadcastNetworkStatus(status : String) {
        Intent().also { intent ->
            intent.action = status
            intent.putExtra("data", "Nothing to see here, move along.")
            activity?.sendBroadcast(intent)
        }
    }

    private fun getNewsFromDb() {
        launch {
            context?.let {
                val articleEntity = AppDataBase.invoke(it).userDetailsDao().getArticleEntity()
                val articleList = ArrayList<Article>()
                Log.d("articleEntity", articleEntity.toString())

                articleList.addAll(articleEntity.map {
                    it.toArticle()
                })
                centralProgressBar.visibility =View.GONE

                binding?.rVSecondVertical?.apply {

                        this.adapter = RecommendedAdapter(articleList,this@NewsFragment)


                }
                binding?.rVMainHorizontial?.apply {
                    this.adapter = AddNewsAdapter(articleList)
                }

            }
        }
    }

    private fun getNewsFromNetwork() {
        val apiServiceIn =
            ApiServices.createInstance().create(ApiServiceIn::class.java)
        val call: Call<NewsResponse> =
            apiServiceIn.news(
                "tesla",
                2021 - 6 - 7,
                "publishedAt",
                "ef10af13e36045b4a965734696d81186", 10
            )
        call.enqueue(object : Callback<NewsResponse> {


            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                isLoading = true
                if (response.isSuccessful) {
                   // progressBar.visibility = View.GONE
                    (activity as DashboardActivity).hideProgressBar()
                    //  centralProgressBar.visibility =View.GONE
                    bottomProgressBar.visibility=View.GONE
                 //   topProgressBar.visibility=View.GONE

                    response.body()?.let {
                        list.addAll(it.articles)

                        updateNewsRecyclerVIew()
                        updateDb()
                         updateRecommendRecyclerView()
                    }
                    Log.d("moshi", "success")

                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
              //  centralProgressBar.visibility=View.GONE
              //  topProgressBar.visibility=View.GONE
            //    progressBar.visibility = View.GONE
                (activity as DashboardActivity).hideProgressBar()
                showToastLong(activity, "error")
                isLoading = true
                Log.d("moshi", "error")
            }


        })
    }

    private fun updateDb() {
        launch {
            val articleEntityList = mutableListOf<ArticleEntity>()
            for (item in list) {
                articleEntityList.add(item.toArticleEntity())
            }
            context?.let {
                val articleEntity = AppDataBase.invoke(it).userDetailsDao()
                    .addArticle(articleEntityList)

                Log.d("articleEntity", articleEntity.toString())
            }
        }
    }

    private fun updateNewsRecyclerVIew() {
        binding?.rVMainHorizontial?.adapter?.notifyDataSetChanged()
    }


    private fun  updateRecommendRecyclerView() {
        binding?.rVSecondVertical?.adapter?.notifyDataSetChanged()
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


