package com.example.brainworkout.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings (
    val gameTimeSeconds: Int,
    val maxResult:Int,
    val minCountOfRightAnswers:Int,
    val minPercentOfRightAnswers:Int
    ):Parcelable