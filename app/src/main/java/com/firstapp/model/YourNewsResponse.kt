package com.firstapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class YourNewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
):Parcelable