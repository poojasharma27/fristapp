package com.firstapp.util

import android.content.Context
import android.content.SharedPreferences

class SessionManagement {

    var sp: SharedPreferences?= null

    constructor(context: Context){
        sp = context.getSharedPreferences("db",Context.MODE_PRIVATE)
    }



    fun setUserEmail(email: String?){
        setSharedPreference("email",email.toString())
    }
    fun getUserEmail():String?{
        return getSharedPreference("email")
    }


    fun setUserPassword(password:String){
        setSharedPreference("password",password)
    }

    fun getPassword(): String?{
        return getSharedPreference("password")
    }
    private fun getSharedPreference(key: String): String{
        val result = sp!!.getString(key,null)

        return result.toString()
    }


     private fun setSharedPreference(key:String, value:String) {
         val editor = sp!!.edit()
         editor.putString(key,value)
         editor.commit()
     }

    fun clearSession(){
        val editor = sp!!.edit()
        editor.clear()
    }
}