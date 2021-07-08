package com.firstapp.ui.home.Add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firstapp.R
import com.firstapp.model.Article
import com.firstapp.util.ExtrasConstants
import java.text.SimpleDateFormat
import java.util.*

class RecommendedAdapter(val  list:List<Article>, val  addNewsFragment: AddNewsFragment) :
    RecyclerView.Adapter<RecommendedAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_recommended,parent,false)
        return RecommendedAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNewsName.text =list.get(position).title
      /* val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",
            Locale.US)
        try {
            val d: Date = dateFormat.parse(list.get(position).publishedAt)
            val print = SimpleDateFormat("dd-MMM-yyyy")
            val strDate = print.format(d)
            Log.e("dd",strDate)
        } catch (ex: ParseException) {
            Log.v("Exception", ex.getLocalizedMessage());
        }*/
      /*  try {
            val format = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss")
            val past = format.parse("2016.02.05 AD at 23:59:30")
            val now = Date()
            val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(now.time - past.time)
            val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(now.time - past.time)
            val hours: Long = TimeUnit.MILLISECONDS.toHours(now.time - past.time)
            val days: Long = TimeUnit.MILLISECONDS.toDays(now.time - past.time)
            //
//          System.out.println(TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime()) + " milliseconds ago");
//          System.out.println(TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime()) + " minutes ago");
//          System.out.println(TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime()) + " hours ago");
//          System.out.println(TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime()) + " days ago");
            if (seconds < 60) {
                println("$seconds seconds ago")
            } else if (minutes < 60) {
                println("$minutes minutes ago")
            } else if (hours < 24) {
                println("$hours hours ago")
            } else {
                println("$days days ago")
            }
        } catch (j: Exception) {
            j.printStackTrace()
        }*/
        holder.tvtime.text =list.get(position).publishedAt

        Glide.with(addNewsFragment)
            .load(list.get(position).urlToImage).into(holder.ivNews);
        holder.cLMain.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {

                val newsDescriptionFragment = NewsDescriptionFragment()
                val fragmentManager: FragmentManager? =
                    addNewsFragment?.fragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                val bundle = Bundle()
                bundle.putParcelable(ExtrasConstants.Users.name, list.get(position))
              //  Log.d("frag",user.toString())


                newsDescriptionFragment.setArguments(bundle)
                fragmentTransaction?.replace(R.id.flMain, newsDescriptionFragment)
                fragmentTransaction?.addToBackStack("null")
                fragmentTransaction?.commit()

            }

        })
    }

    override fun getItemCount(): Int {
        return  list.size
    }



    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var tvNewsName: TextView = itemView.findViewById(R.id.tvNewsName)
        var tvtime: TextView = itemView.findViewById(R.id.tvtime)
        var ivNews: ImageView = itemView.findViewById(R.id.ivNews)
        var cLMain: RelativeLayout = itemView.findViewById(R.id.cLMain)

    }

}