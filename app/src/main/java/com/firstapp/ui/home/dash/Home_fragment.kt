package com.firstapp.ui.home.dash

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firstapp.R
import com.firstapp.model.User
import com.firstapp.ui.home.Shared_With_Adapter
import com.firstapp.util.Constants
import com.firstapp.util.ExtrasConstants


class Home_fragment : Fragment(){
lateinit var  rvMain: RecyclerView
lateinit var adapter: Shared_With_Adapter
    lateinit var  toolbar: LinearLayout
    lateinit var dashboardNames: ArrayList<String>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_home,container,false)
        val user: User? = arguments?.getParcelable(ExtrasConstants.Users.name) as User?
        val tvuser :TextView = v.findViewById(R.id.tvuser)
        tvuser.text =user?.email +"\n" +user?.password
        rvMain = v.findViewById(R.id.rVDashboardd)
    toolbar= v.findViewById(R.id.toolbar)
        toolbar.setBackgroundResource(R.color.darkBlue);
  val ivBackPress: ImageView =v.findViewById(R.id.ivBackPress)
        ivBackPress.visibility =View.INVISIBLE
        val tvAppBarName: TextView =v.findViewById(R.id.tvAppBarName)
        tvAppBarName.text = "Cloudy"
      dashboardNames  = ArrayList()
        dashboardNames.add("Design File")
        dashboardNames.add("Dribbble")
        genrateList()
        return v.rootView
    }

    private fun genrateList() {
        adapter = Shared_With_Adapter(
            dashboardNames,
this        )
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rvMain.layoutManager=linearLayoutManager
        rvMain.setAdapter(adapter)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.side_bar_toolbar, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}