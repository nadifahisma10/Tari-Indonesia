package com.example.tariindonesia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable