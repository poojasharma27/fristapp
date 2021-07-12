package com.firstapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User( var email :String,
            var password : String): Parcelable {


}
