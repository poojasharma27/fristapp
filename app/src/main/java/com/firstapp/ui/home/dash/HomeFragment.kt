package com.firstapp.ui.home.dash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.firstapp.R
import com.firstapp.databinding.FragmentHomeBinding
import com.firstapp.network.model.User
import com.firstapp.ui.home.SharedWithAdapter
import com.firstapp.util.ExtrasConstants
import com.firstapp.util.ItemClickListener


class HomeFragment : Fragment() ,ItemClickListener{
    private var binding: FragmentHomeBinding? = null
    private var adapter: SharedWithAdapter? = null
    private var toolbar: LinearLayout? = null
    private var dashboardNames: ArrayList<String>? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding?.root
        val user: User? = arguments?.getParcelable(ExtrasConstants.Users.name) as User?
        binding?.tvuser?.text = user?.email?.plus(user.password)
        toolbar = view?.findViewById(R.id.toolbar)
        toolbar?.setBackgroundResource(R.color.darkBlue)
        val ivBackPress: ImageView? = view?.findViewById(R.id.ivBackPress)
        ivBackPress?.visibility = View.INVISIBLE
        val tvAppBarName: TextView? = view?.findViewById(R.id.tvAppBarName)
        tvAppBarName?.text = "Cloudy"
        dashboardNames = ArrayList()
        dashboardNames?.add("Design File")
        dashboardNames?.add("Dribble")
        getList()
        return view
    }

    private fun getList() {
        adapter = dashboardNames?.let {
            SharedWithAdapter(
                it,
                activity ,
            this)
        }
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding?.rVDashboardd?.layoutManager = linearLayoutManager
        binding?.rVDashboardd?.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.side_bar_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewClicked(view: View, position: Int) {
        val designFileFragment = DesignFileFragment()
        val fragmentManager: FragmentManager? =
            activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.flMain, designFileFragment)
        fragmentTransaction?.addToBackStack("null")
        fragmentTransaction?.commit()
    }

}