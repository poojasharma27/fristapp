package com.firstapp.ui.home.news

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.firstapp.R
import com.firstapp.databinding.FragmentAddNewsBinding
import com.firstapp.network.ApiServiceIn
import com.firstapp.network.ApiServices
import com.firstapp.network.model.Article
import com.firstapp.network.model.NewsResponse
import com.firstapp.util.ExtrasConstants
import com.firstapp.util.ItemClickListener
import com.firstapp.util.showToastLong
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment(), ItemClickListener {
    private var binding: FragmentAddNewsBinding? = null
    private var addNewsAdapter: AddNewsAdapter? = null
    private var recommendedAdapter: RecommendedAdapter? = null
    private  var list: ArrayList<Article> = ArrayList<Article>()

    override fun onResume() {
        super.onResume()

    }

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
        setupRecyclerView()

        //making the news api calls
        getNews()

    }

    private fun setupRecyclerView() {

        binding?.rVMainHorizontial?.apply {
            this.adapter = AddNewsAdapter(list)
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
                "ef10af13e36045b4a965734696d81186"
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
      binding?.rVMainHorizontial?.adapter?.notifyDataSetChanged()
    }

    private fun setRecommendList(list: List<Article>) {
        recommendedAdapter = RecommendedAdapter(list, activity as? Context, this)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.rVSecondVertical?.layoutManager = linearLayoutManager
        binding?.rVSecondVertical?.adapter = recommendedAdapter
    }

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


}