package com.firstapp.ui.home.Add

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.firstapp.R
import com.firstapp.databinding.FragmentNewsDescripationBinding
import com.firstapp.model.Article
import com.firstapp.model.User
import com.firstapp.util.ExtrasConstants
import kotlinx.android.synthetic.main.fragment_news_descripation.*

class NewsDescriptionFragment :Fragment() {
lateinit var fragmentNewsDescripationBinding: FragmentNewsDescripationBinding
lateinit var tvTitle: TextView
lateinit var tvDes: TextView
lateinit var tvTime: TextView
lateinit var tvauthore: TextView
lateinit var  toolbar : LinearLayout
lateinit var  ivDes : ImageView
lateinit var  tvPressBack : ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentNewsDescripationBinding= FragmentNewsDescripationBinding.inflate(inflater,container,false)
        val view = fragmentNewsDescripationBinding.root
        val user: Article? = arguments?.getParcelable(ExtrasConstants.Users.name)
        tvTitle =view.findViewById(R.id.tvTitle)
        tvTime =view.findViewById(R.id.tvTime)
        tvDes =view.findViewById(R.id.tvDes)
        ivDes =view.findViewById(R.id.ivNews)
        tvauthore =view.findViewById(R.id.tvBy)
        tvDes.text = user?.description
        tvTitle.text =user?.title
        tvTime .text =user?.publishedAt
        tvauthore .text =user?.author
        toolbar= view.findViewById(R.id.toolbar)
        toolbar.setBackgroundResource(R.color.lightBlack);
        val tvAppBarName: TextView =view.findViewById(R.id.tvAppBarName)
        tvAppBarName.setTextColor(Color.parseColor("#000000"))
        tvPressBack =view.findViewById(R.id.ivBackPress)
        tvPressBack.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
         fragmentManager?.popBackStack()
            }

        })
        Glide.with(this).load(user?.urlToImage).into(ivDes)
        return  view
    }
}