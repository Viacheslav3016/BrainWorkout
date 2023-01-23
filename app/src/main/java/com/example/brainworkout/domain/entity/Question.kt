package com.example.brainworkout.domain.entity

data class Question(
    val options:List<Int>,
    val visibleNum:Int,
    val sum:Int
)