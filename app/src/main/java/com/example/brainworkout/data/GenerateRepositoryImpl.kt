package com.example.brainworkout.data

import com.example.brainworkout.domain.entity.GameSettings
import com.example.brainworkout.domain.entity.Level
import com.example.brainworkout.domain.entity.Question
import com.example.brainworkout.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GenerateRepositoryImpl : GameRepository {
    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun GenerateQuestion(maxSumValue: Int, countOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - countOptions, MIN_ANSWER_VALUE)
        val to = min(maxSumValue - 1, rightAnswer - countOptions)
        while (options.size < countOptions) {
            options.add(Random.nextInt(from, to))
        }
        return Question(options.toList(), visibleNumber, sum)
    }

    override fun GetGameSettings(level: Level): GameSettings {
       return when (level) {
            Level.TEST -> {
                GameSettings(
                    10,
                    10,
                    10,
                    50
                )
            }
            Level.EASY -> {
                GameSettings(60, 20, 20, 70)
            }
            Level.MIDDLE -> {
                GameSettings(60, 40, 40, 85)
            }
            Level.HARD -> {
                GameSettings(50, 60, 60, 95)
            }

        }
    }
}