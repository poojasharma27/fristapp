package com.firstapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.firstapp.R
import com.firstapp.network.model.User
import com.firstapp.ui.home.news.NewsFragment
import com.firstapp.ui.home.dashboard.HomeFragment
import com.firstapp.ui.home.bookmarked.BookMarkFragment
import com.firstapp.util.ExtrasConstants
import com.firstapp.util.SessionManagement
import com.google.android.material.bottomnavigation.BottomNavigationView


class DashboardActivity : AppCompatActivity() {
  private  var  bottomNavigationView: BottomNavigationView?=null
    private lateinit var sessionManagement: SessionManagement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        sessionManagement = SessionManagement(this)
        bottomNavigationView= findViewById(R.id.bNView)
        val user : User = intent.getParcelableExtra<User>(ExtrasConstants.Users.name) as User

        Log.d("frag",user.email+user.password)
        val bundle = Bundle()
        bundle.putParcelable(ExtrasConstants.Users.name, user)
        Log.d("frag",user.toString())

        val homeFragment = HomeFragment()
        setCurrentFragment(homeFragment)
        homeFragment.arguments=bundle
        bottomNavigationView?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.iDashbord->{
                    setCurrentFragment(HomeFragment())}
                R.id.iAdd->{
                    setCurrentFragment(NewsFragment())

                }
                R.id.iPerson->{
                    setCurrentFragment(BookMarkFragment())
                }
              /*  R.id.iLogout->{
                   sessionManagement.clearSession()
                }*/
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flMain, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun switchContent(fragment: Fragment) {
      val  mContent: FragmentTransaction= supportFragmentManager.beginTransaction()
           mContent .replace(R.id.flMain, fragment).commit()

    }

}