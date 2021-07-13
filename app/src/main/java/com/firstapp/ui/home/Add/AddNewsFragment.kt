package com.firstapp.ui.home.Add

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.firstapp.databinding.FragmentAddNewsBinding
import com.firstapp.network.ApiServiceIn
import com.firstapp.network.ApiServices
import com.firstapp.network.model.Article
import com.firstapp.network.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddNewsFragment : Fragment() {

    var binding: FragmentAddNewsBinding? = null
    var addNewsAdapter: AddNewsAdapter? = null
    var recommendedAdapter: RecommendedAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNewsBinding.inflate(inflater, container, false)
        val view = binding?.root

        getNews()
        return view
    }

    private fun getNews() {
        val apiServiceIn =
            ApiServices.getRetrofitInstance().create(ApiServiceIn::class.java)
        val call: Call<NewsResponse> =
            apiServiceIn.news(
                "tesla",
                2021 - 6 - 7,
                "publishedAt",
                "ef10af13e36045b4a965734696d81186"
            )
        call.enqueue(object : Callback<NewsResponse> {

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                //TODO - make an extension functions showLongToast and showShortToast
                Toast.makeText(activity, "error", Toast.LENGTH_LONG).show()
                Log.d("moshi", "error")
            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    genratedNewsList(response.body()!!.articles)
                    genratedRecommmededList(response.body()!!.articles)
                    /*     Toast.makeText(activity, "" + response.body(), Toast.LENGTH_LONG)
                             .show()*/
                    Log.d("moshi", "success")

                }
            }

        })
    }

    private fun genratedNewsList(list: List<Article>) {
        addNewsAdapter = AddNewsAdapter(list, activity as? Context )
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding?.rVMainHorizontial?.layoutManager = linearLayoutManager
        binding?.rVMainHorizontial?.adapter = addNewsAdapter
    }

    private fun genratedRecommmededList(list: List<Article>) {
        recommendedAdapter = RecommendedAdapter(list, this)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.rVSecondVertical?.layoutManager = linearLayoutManager
        binding?.rVSecondVertical?.adapter = recommendedAdapter
    }


}