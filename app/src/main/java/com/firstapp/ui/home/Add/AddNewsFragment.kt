package com.firstapp.ui.home.Add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.databinding.FragmentAddNewsBinding
import com.firstapp.model.Article
import com.firstapp.model.YourNewsResponse
import com.firstapp.network.ApiSerives
import com.firstapp.network.ApiServiceIn
import kotlinx.android.synthetic.main.fragment_add_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddNewsFragment :Fragment() {
lateinit var  fragmentAddNewsBinding: FragmentAddNewsBinding
lateinit var  yourNewsRecycler :RecyclerView
lateinit var  addNewsAdapter: AddNewsAdapter
lateinit var recommendedAdapter :RecommendedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     //   val v:View = inflater.inflate(R.layout.fragment_add_news,container,false)
        fragmentAddNewsBinding = FragmentAddNewsBinding.inflate(inflater,container,false)
        val view = fragmentAddNewsBinding.root
        hitAddNewsApi()
   hitAddNewsApi()

        return view
    }

    private fun hitAddNewsApi() {
        val apiServiceIn=
        ApiSerives.getRetrofitInstance().create(ApiServiceIn::class.java)
        val call: Call<YourNewsResponse> =
            apiServiceIn.newsresponse("tesla", 2021 - 6 - 7,"publishedAt","ef10af13e36045b4a965734696d81186")
        call.enqueue(object : Callback<YourNewsResponse> {

            override fun onFailure(call: Call<YourNewsResponse>, t: Throwable) {
               Toast.makeText(activity, "error", Toast.LENGTH_LONG).show()
                Log.d("moshi", "error")
            }

            override fun onResponse(call: Call<YourNewsResponse>, response: Response<YourNewsResponse>) {
                if (response.isSuccessful) {
                    genratedNewsList(response.body()!!.articles)
                    genratedRecommmededList(response.body()!!.articles)
               /*     Toast.makeText(activity, "" + response.body(), Toast.LENGTH_LONG)
                        .show()*/
                    Log.d("moshi", "success")

                }
            }

        })    }

    private  fun genratedNewsList( list:List<Article>){
        addNewsAdapter = AddNewsAdapter(list,this)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rVMainHorizontial.layoutManager = linearLayoutManager
        rVMainHorizontial.setAdapter(addNewsAdapter)
    }
  private  fun genratedRecommmededList( list:List<Article>){
      recommendedAdapter = RecommendedAdapter(list,this)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rVSecondVertical.layoutManager = linearLayoutManager
        rVSecondVertical.setAdapter(recommendedAdapter)
    }


}