package com.example.brainworkout.domain.entity

data class GameSettings (
    val gameTimeSeconds: Int,
    val maxResult:Int,
    val minCountOfRightAnswers:Int,
    val minPercentOfRightAnswers:Int
    )