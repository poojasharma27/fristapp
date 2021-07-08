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
import androidx.appcompat.app.AppCompatActivity
import com.firstapp.R
import com.firstapp.model.User
import com.firstapp.network.ApiSerives
import com.firstapp.network.ApiServiceIn
import com.firstapp.ui.home.DashboardActivity
import com.firstapp.util.Constants
import com.firstapp.util.ExtrasConstants
import com.firstapp.util.SessionManagement
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var loginbtn: TextView
    lateinit var sessionManagement: SessionManagement
    lateinit var emailed: TextInputEditText
    lateinit var passed: TextInputEditText
    lateinit var signup: TextView
    lateinit var emailStr: String
    lateinit var passwordStr: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.e("lifecycle", "onCreate")
        loginbtn = findViewById(R.id.loginbtn)
        emailed = findViewById(R.id.edemail)
        passed = findViewById(R.id.edpassword)
        signup = findViewById(R.id.tvdonto)
        emailStr = emailed.text.toString()
        passwordStr = passed.text.toString()
        signup.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))

            }

        })
        sessionManagement = SessionManagement(this)
        loginbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (emailed.text.toString()
                        .equals(sessionManagement.getUserEmail()) && passed.text.toString()
                        .equals(sessionManagement.getPassword())
                ) {
                /// hitLoginApi()
                val user = User(emailed.text.toString(), passed.text.toString())
                   /* val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                    intent.putExtra("Users", user)
                    startActivity(intent)*/
                    startActivity(Intent(this@LoginActivity, DashboardActivity::class.java).putExtra(
                        ExtrasConstants.Users.name, user))
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
}

