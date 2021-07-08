package com.firstapp.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
@Parcelize
 class User( var email :String,
             var password : String): Parcelable {


}
