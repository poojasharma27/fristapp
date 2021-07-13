package com.firstapp.ui.register

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.firstapp.R
import com.firstapp.base.BaseActivity
import com.firstapp.db.AppDataBase
import com.firstapp.network.model.User
import com.firstapp.ui.home.DashboardActivity
import com.firstapp.util.ExtrasConstants
import com.firstapp.util.SessionManagement
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.*
import java.io.FileInputStream
import java.io.FileOutputStream


class LoginActivity : BaseActivity() {
    lateinit var loginbtn: TextView
    lateinit var sessionManagement: SessionManagement
    lateinit var emailed: TextInputEditText
    lateinit var passed: TextInputEditText
    lateinit var signup: TextView
    lateinit var emailStr: String
    lateinit var passwordStr: String
    lateinit var tvforgot: TextView
    var file = "MyInternalData"
    lateinit var data: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.e("lifecycle", "onCreate")
        loginbtn = findViewById(R.id.loginbtn)
        emailed = findViewById(R.id.edemail)
        passed = findViewById(R.id.edpassword)
        signup = findViewById(R.id.tvdonto)
        tvforgot = findViewById(R.id.tvforgot)
        emailStr = emailed.text.toString()
        passwordStr = passed.text.toString()
        signup.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))

            }

        })
        launch {
            this.let {
                val userDeatails =
                    AppDataBase.invoke(this@LoginActivity).userDetailsDao().getUserDetails()
                Log.d("DetailsTAG", userDeatails.toString())

            }

        }
        sessionManagement = SessionManagement(this)
        loginbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (emailed.text.toString() == sessionManagement.getUserEmail() && passed.text.toString() == sessionManagement.getPassword()
                ) {
                    /// hitLoginApi()
                    val user = User(emailed.text.toString(), passed.text.toString())
                    startActivity(
                        Intent(this@LoginActivity, DashboardActivity::class.java).putExtra(
                            ExtrasConstants.Users.name, user
                        )
                    )
                    try {
                        val fin: FileInputStream = openFileInput(file)
                        var c: Int
                        var temp = ""
                        while (fin.read().also { c = it } != -1) {
                            temp = temp + Character.toString(c.toChar())
                        }
                        Log.d("temp", temp)
                        Toast.makeText(baseContext, "file read", Toast.LENGTH_SHORT).show()
                    } catch (e: java.lang.Exception) {
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "", Toast.LENGTH_LONG).show()
                }
            }


        })
        val spannable = SpannableString("Do not have an Account? Signup")
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#0096e0")),
            24, // start
            30, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        signup.text = spannable
        tvforgot.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                try {
                    data = emailed.text.toString()
                    val fOut: FileOutputStream = openFileOutput(file, MODE_PRIVATE)
                    fOut.write(data.toByteArray())
                    fOut.close()
                    Toast.makeText(baseContext, "file saved", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                }
            }

        })

    }


    override fun onStart() {
        super.onStart()
        Log.e("lifecycle", "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.e("lifecycle", "onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.e("lifecycle", "onPause")


    }

    override fun onStop() {
        super.onStop()
        Log.e("lifecycle", "onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("lifecycle", "onDestroy")

    }

    override fun onRestart() {
        Log.e("lifecycle", "onRestart")

        super.onRestart()
    }

    /* fun hitLoginApi() {
         val service: ApiServiceIn =
             ApiSerives.getRetrofitInstance().create(ApiServiceIn::class.java)
         *//*    val jsonObject = JSONObject()
            jsonObject.addProperty("mobile", mobile)
            jsonObject.addProperty("password",password)*//*
        ///  val jsonObject = JSONObje

        val call: Call<LoginResponce> =
            service.signIn(emailed.text.toString(), passed.text.toString())
        call.enqueue(object : Callback<LoginResponce> {

            override fun onFailure(call: Call<LoginResponce>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "error", Toast.LENGTH_LONG).show()
                Log.d("moshi", "error")
            }

            override fun onResponse(call: Call<LoginResponce>, response: Response<LoginResponce>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@LoginActivity, "" + response.body(), Toast.LENGTH_LONG)
                        .show()
                    Log.d("moshi", "success")

                }
            }

        })
    }*/


    /* private fun getTasks() {
         class GetTasks :
             AsyncTask<Void?, Void?, List<UserDeatails>>() {


             override fun onPostExecute(tasks: List<UserDeatails>) {
                 super.onPostExecute(tasks)
                 Log.d("DetailsTAG", tasks.toString())
             }


             override fun doInBackground(vararg params: Void?): List<UserDeatails> {
                 val userDeatails =
                     AppDataBase.invoke(this@LoginActivity).userDeatilsDao().getuserDeatils()

                 return userDeatails
             }
         }

         val gt = GetTasks()
         gt.execute()
     }*/
}

