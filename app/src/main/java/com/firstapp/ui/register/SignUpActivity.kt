package com.firstapp.ui.register

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import com.firstapp.base.BaseActivity
import com.firstapp.databinding.ActivitySignupBinding
import com.firstapp.db.AppDataBase
import com.firstapp.network.model.User
import com.firstapp.db.entities.UserEntity
import com.firstapp.ui.home.DashboardActivity
import com.firstapp.util.ExtrasConstants
import com.firstapp.util.SessionManagement
import kotlinx.coroutines.launch

class SignUpActivity : BaseActivity() {
   private lateinit var userDetails: UserEntity
  private  var binding : ActivitySignupBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view =binding?.root
        setContentView(view)

   val sessionManagement = SessionManagement(this)
      binding?.signbtn?.setOnClickListener {
          sessionManagement.setUserEmail(binding?.edemail?.text.toString())
          sessionManagement.setUserPassword(binding?.edpassword?.text.toString())
          launch {
              userDetails = UserEntity(
                  binding?.edemail?.text.toString(),
                  binding?.edpassword?.text.toString()
              )
              this.let {
                  AppDataBase(this@SignUpActivity).userDetailsDao().addDetails(userDetails)
                  Log.d("DetailsTAG", userDetails.toString())


              }
          }
          val user = User(binding?.edemail?.text.toString(), binding?.edpassword?.text.toString())
          startActivity(
              Intent(this@SignUpActivity, DashboardActivity::class.java).putExtra(
                  ExtrasConstants.Users.name, user
              )
          )
      }

        Log.e("lifecycle","onCreate2")
       /* button = findViewById(R.id.btn)
        button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                startActivity(Intent(this@MainActivity2,MainActivity::class.java))
            }

        })*/

        val spannable = SpannableString("Already have an Account? Login")
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#0096e0")),
            25, // start
            30, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
      binding?.tvdonto?.text=spannable
    }

    override fun onStart() {
        Log.e("lifecycle","onStart2")

        super.onStart()
    }

    override fun onResume() {
        Log.e("lifecycle","onResume2")

        super.onResume()
    }

    override fun onPause() {
        Log.e("lifecycle","onPause2")

        super.onPause()
    }

    override fun onStop() {
        Log.e("lifecycle","onStop2")

        super.onStop()
    }

    override fun onDestroy() {
        Log.e("lifecycle","onDestroy2")

        super.onDestroy()
    }

    override fun onRestart() {
        Log.e("lifecycle","onRestart2")

        super.onRestart()
    }

    /*private fun saveData(userDetails: UserDetails){
        class  SaveNote : AsyncTask<Void, Void ,Void>(){

            override fun doInBackground(vararg params: Void?): Void? {
                AppDataBase(this@SignUpActivity!!).userDetailsDao().addDetails(userDetails)
             return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(this@SignUpActivity,"Note Save",Toast.LENGTH_LONG).show()
                Log.d("DetailsTAG",userDetails.toString())
            }
        }
        SaveNote().execute()
    }*/
}