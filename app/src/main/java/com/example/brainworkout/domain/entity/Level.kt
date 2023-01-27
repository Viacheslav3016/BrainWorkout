package com.example.brainworkout.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Level:Parcelable {
    TEST,EASY,MIDDLE,HARD
}