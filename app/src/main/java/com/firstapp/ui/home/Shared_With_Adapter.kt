package com.firstapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.R
import com.firstapp.ui.home.dash.Design_File_Fragment
import com.firstapp.ui.home.dash.Home_fragment

class Shared_With_Adapter(private  var mData:List<String>, private  var homeFragment: Home_fragment):
    RecyclerView.Adapter<Shared_With_Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v:View = LayoutInflater.from(parent.context).inflate(R.layout.item_shared_with, parent, false);
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text =mData.get(position)
        holder.rlMain.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val designFileFragment: Design_File_Fragment = Design_File_Fragment()
            val fragmentManager: FragmentManager? =
                    homeFragment?.fragmentManager
                val fragmentTransaction = fragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.flMain, designFileFragment)
                fragmentTransaction?.addToBackStack("null")
                fragmentTransaction?.commit()


                     }

        })
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
     var tvName: TextView = itemView.findViewById(R.id.tvDesign)
     var rlMain: RelativeLayout = itemView.findViewById(R.id.rlMain)


    }

    private fun switchFragment(newFragment: Fragment) {
        if (homeFragment.context == null) return
        if (homeFragment.context is DashboardActivity) {
            val feeds: DashboardActivity =homeFragment.context as DashboardActivity
            feeds.switchContent(newFragment)
        }
    }
}