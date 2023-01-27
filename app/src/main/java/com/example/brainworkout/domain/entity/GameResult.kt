package com.example.brainworkout.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val isWinner:Boolean,
    val countOfRightAnswers:Int,
    val countOfQuestions:Int,
    val GameSettings:GameSettings
    ):Parcelable

