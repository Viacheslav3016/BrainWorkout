package com.example.brainworkout.domain.repository

import com.example.brainworkout.domain.entity.GameSettings
import com.example.brainworkout.domain.entity.Level
import com.example.brainworkout.domain.entity.Question

interface GameRepository {

    fun GenerateQuestion(maxSumValue:Int,
    countOptions:Int):Question

     fun GetGameSettings(level: Level):GameSettings
}