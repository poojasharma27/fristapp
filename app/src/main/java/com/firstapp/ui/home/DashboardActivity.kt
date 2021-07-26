package com.firstapp.ui.home


import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.firstapp.R
import com.firstapp.databinding.ActivityDashboardBinding
import com.firstapp.network.model.User
import com.firstapp.ui.home.bookmarked.BookMarkFragment
import com.firstapp.ui.home.camera.CameraFragment
import com.firstapp.ui.home.dashboard.HomeFragment
import com.firstapp.ui.home.image.ImagesFragment
import com.firstapp.ui.home.news.NewsFragment
import com.firstapp.util.*
import com.google.android.material.bottomnavigation.BottomNavigationView


class DashboardActivity : AppCompatActivity(),FragmentClickListener {

    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var sessionManagement: SessionManagement
    private lateinit var broadCastReceiver: ConnectivityReceiver
    var binding:ActivityDashboardBinding?=null
    lateinit var  progressBar:RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        sessionManagement = SessionManagement(this)
        bottomNavigationView = findViewById(R.id.bNView)
        progressBar = findViewById(R.id.progressBarLayout)
        val user: User = intent.getParcelableExtra<User>(ExtrasConstants.Users.name) as User

        Log.d("frag", user.email + user.password)
        val bundle = Bundle()
        bundle.putParcelable(ExtrasConstants.Users.name, user)
        Log.d("frag", user.toString())
        broadCastReceiver = ConnectivityReceiver()
        val homeFragment = HomeFragment()
        setCurrentFragment(homeFragment)
        homeFragment.arguments = bundle
        bottomNavigationView?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.iDash -> {
                    setCurrentFragment(HomeFragment())
                }
                R.id.iAdd -> {
                    setCurrentFragment(NewsFragment())

                }
                R.id.iPerson -> {
                    setCurrentFragment(BookMarkFragment())
                }
                R.id.iImage -> {
                    setCurrentFragment(ImagesFragment())
                }
                R.id.camreaItem -> {
                    setCurrentFragment(CameraFragment())
                }

                /*  R.id.iLogout->{
                     sessionManagement.clearSession()
                  }*/
            }
            true
        }
        registerNetwork()

    }

    fun showProgressBar(){
        progressBar.visibility= View.VISIBLE
    }

    fun hideProgressBar(){
        progressBar.visibility = View.GONE
    }

    private fun setCurrentFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flMain, fragment).addToBackStack(null).commit()
    }

    protected fun registerNetwork() {
       val filter =IntentFilter()
        filter.addAction(connected)
        filter.addAction(disconnected)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            this.registerReceiver(
                broadCastReceiver,
                filter
            )
        }else{
            this.registerReceiver(
                broadCastReceiver,
                filter
            )
        }
        /* IntentFilter(Intent.ACTION_POWER_CONNECTED).also {
             registerReceiver(broadCastReceiver,it)
         }*/
    }

    protected fun unRegisterNetwork() {
        unregisterReceiver(broadCastReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        unRegisterNetwork()
    }



    override fun onFragmentTransaction(fragment: Fragment) {
        this.supportFragmentManager.beginTransaction().replace(R.id.flMain, fragment).addToBackStack(null).commit()
    }

}