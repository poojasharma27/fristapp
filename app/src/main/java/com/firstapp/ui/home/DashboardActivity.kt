package com.firstapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.firstapp.R
import com.firstapp.model.User
import com.firstapp.ui.home.Add.AddNewsFragment
import com.firstapp.ui.home.dash.Home_fragment
import com.firstapp.ui.home.profile.Profile_Fragment
import com.firstapp.util.Constants
import com.firstapp.util.ExtrasConstants
import com.firstapp.util.SessionManagement
import com.google.android.material.bottomnavigation.BottomNavigationView


class DashboardActivity : AppCompatActivity() {
lateinit var  bottomNavigationView: BottomNavigationView
    lateinit var sessionManagement: SessionManagement
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

        val fragobj = Home_fragment()
        setCurrentFragment(fragobj)
        fragobj.setArguments(bundle)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.iDashbord->{
                    setCurrentFragment(Home_fragment())}
                R.id.iAdd->{
                    setCurrentFragment(AddNewsFragment())

                }
                R.id.iPerson->{
                    setCurrentFragment(Profile_Fragment())
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
        //slidemenu.showContent()
    }

}