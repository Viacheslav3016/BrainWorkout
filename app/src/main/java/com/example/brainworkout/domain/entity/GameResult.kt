package com.example.brainworkout.domain.entity

data class GameResult(
    val isWinner:Boolean,
    val countOfRightAnswers:Int,
    val countOfQuestions:Int,
    val GameSettings:GameSettings
    )

