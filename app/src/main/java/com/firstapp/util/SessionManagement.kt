package com.firstapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SessionManagement {

    var sp: SharedPreferences?= null
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

  /*  constructor(context: Context){
        sp = context.getSharedPreferences("db",Context.MODE_PRIVATE)
    }*/


  constructor(context: Context){

     sp   =   EncryptedSharedPreferences.create(
            "encrypted_preferences", // fileName
            masterKeyAlias, // masterKeyAlias
            context, // context
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, // prefKeyEncryptionScheme
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM // prefvalueEncryptionScheme
        )

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