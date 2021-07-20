package com.firstapp.ui.home


import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.firstapp.R
import com.firstapp.network.model.User
import com.firstapp.ui.home.bookmarked.BookMarkFragment
import com.firstapp.ui.home.dashboard.HomeFragment
import com.firstapp.ui.home.news.NewsFragment
import com.firstapp.util.ConnectivityReceiver
import com.firstapp.util.ExtrasConstants
import com.firstapp.util.SessionManagement
import com.google.android.material.bottomnavigation.BottomNavigationView


class DashboardActivity : AppCompatActivity() {
    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var sessionManagement: SessionManagement
    private lateinit var broadCastReceiver: ConnectivityReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        sessionManagement = SessionManagement(this)
        bottomNavigationView = findViewById(R.id.bNView)
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
                R.id.iDashbord -> {
                    setCurrentFragment(HomeFragment())
                }
                R.id.iAdd -> {
                    setCurrentFragment(NewsFragment())

                }
                R.id.iPerson -> {
                    setCurrentFragment(BookMarkFragment())
                }
                /*  R.id.iLogout->{
                     sessionManagement.clearSession()
                  }*/
            }
            true
        }
        registerNetwork()
    }

    private fun setCurrentFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flMain, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    protected fun registerNetwork() {
        /*val filter =IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)*/
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
         this . registerReceiver(
                broadCastReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
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

}