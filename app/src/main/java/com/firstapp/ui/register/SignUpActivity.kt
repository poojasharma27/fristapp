package com.firstapp.ui.register

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.firstapp.R
import com.firstapp.ui.home.DashboardActivity
import com.firstapp.util.SessionManagement
import com.google.android.material.textfield.TextInputEditText

class SignUpActivity : AppCompatActivity() {
lateinit var button:Button
lateinit var nameev : TextInputEditText
lateinit var emailev : TextInputEditText
lateinit var phnev : TextInputEditText
lateinit var passwordev : TextInputEditText
lateinit var confirmev : TextInputEditText
    lateinit var signbtn: TextView
    lateinit var login: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        nameev = findViewById(R.id.edname)
        phnev = findViewById(R.id.edmobilenumber)
        emailev = findViewById(R.id.edemail)
        passwordev = findViewById(R.id.edpassword)
        confirmev = findViewById(R.id.edconfrimpassword)
        signbtn = findViewById(R.id.signbtn)
        login = findViewById(R.id.tvdonto)
        val emailStr: String = emailev.text.toString()
        val passStr: String =passwordev.text.toString()
   val sessionManagement = SessionManagement(this)
        signbtn.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                sessionManagement.setUserEmail(emailev.text.toString())
                sessionManagement.setUserPassword(passwordev.text.toString())
                startActivity(Intent(this@SignUpActivity, DashboardActivity::class.java))
2
            }

        })


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
        login.text=spannable
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
}