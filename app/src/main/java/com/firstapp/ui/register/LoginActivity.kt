package com.firstapp.ui.register

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firstapp.databinding.ActivityLoginBinding
import com.firstapp.network.model.User
import com.firstapp.ui.home.DashboardActivity
import com.firstapp.util.ExtrasConstants
import com.firstapp.util.SessionManagement
import com.firstapp.util.showToastShort
import kotlinx.coroutines.*
import java.io.FileInputStream
import java.io.FileOutputStream


class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManagement: SessionManagement
    private var file = "MyInternalData"
    private lateinit var data: String
    var binding: ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)
        Log.e("lifecycle", "onCreate")
        binding?.tvdonto?.setOnClickListener {
            clickEvent()
        }
        /*launch {
            this.let {
                val userDetail =
                    AppDataBase.invoke(this@LoginActivity).userDetailsDao().getUserDetails()
                Log.d("DetailsTAG", userDetail.toString())

            }

        }*/
        sessionManagement = SessionManagement(this)
        binding?.loginbtn?.setOnClickListener {
            if (binding?.edemail?.text.toString() == sessionManagement.getUserEmail() && binding?.edpassword?.text.toString() == sessionManagement.getPassword()
            ) { /// hitLoginApi()

                val user =
                    User(binding?.edemail?.text.toString(), binding?.edpassword?.text.toString())
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
                        temp += c.toChar().toString()
                    }
                    Log.d("temp", temp)
                    baseContext?.let {
                        showToastShort(it, "file read")
                    }
                } catch (e: java.lang.Exception) {
                }
            } else {
                baseContext?.let {
                    showToastShort(it, " ")
                }
            }
        }
        val spannable = SpannableString("Do not have an Account? Signup")
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#0096e0")),
            24, // start
            30, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        binding?.tvdonto?.text = spannable
        binding?.tvforgot?.setOnClickListener {
            try {
                data = binding?.edemail?.text.toString()
                val fOut: FileOutputStream = openFileOutput(file, MODE_PRIVATE)
                fOut.write(data.toByteArray())
                fOut.close()
                baseContext?.let {
                    showToastShort(it, "file saved")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun clickEvent() {
        startActivity(
            Intent(
                this@LoginActivity,
                SignUpActivity::class.java
            )
        )
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


    /* private fun getTasks() {
         class GetTasks :
             AsyncTask<Void?, Void?, List<UserDetail>>() {


             override fun onPostExecute(tasks: List<UserDetail>) {
                 super.onPostExecute(tasks)
                 Log.d("DetailsTAG", tasks.toString())
             }


             override fun doInBackground(vararg params: Void?): List<UserDetail> {
                 val userDetail =
                     AppDataBase.invoke(this@LoginActivity).userDetailsDao().getUserDetail()

                 return userDetails
             }
         }

         val gt = GetTasks()
         gt.execute()
     }*/

  
}

