package com.firstapp.ui.home.Add

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.firstapp.R
import com.firstapp.databinding.FragmentNewsDescripationBinding
import com.firstapp.network.model.Article
import com.firstapp.util.ExtrasConstants

class NewsDescriptionFragment :Fragment() {
  private var binding: FragmentNewsDescripationBinding?= null

   private var  toolbar : LinearLayout?=null
  private var  tvPressBack : ImageView ?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentNewsDescripationBinding.inflate(inflater,container,false)
        val view = binding?.root
        val user: Article? = arguments?.getParcelable(ExtrasConstants.Users.name)
        user?.run {
           binding?.tvDes?.text = user.description
            binding?.tvTitle?.text =user.title
            binding?.tvTime?.text =user.publishedAt
            if (!user.author.isNullOrEmpty() ) {
                binding?.tvBy?.text = user.author
            }

        }
        binding?.ivNews?.let { Glide.with(this).load(user?.urlToImage).into(it) }

       toolbar= view?.findViewById(R.id.toolbar)
        toolbar?.setBackgroundResource(R.color.lightBlack)
        val tvAppBarName: TextView? =view?.findViewById(R.id.tvAppBarName)
        tvAppBarName?.setTextColor(Color.parseColor("#000000"))
        tvPressBack =view?.findViewById(R.id.ivBackPress)
        tvPressBack?.setOnClickListener { activity?.supportFragmentManager?.popBackStack() }

        return  view
    }
}
