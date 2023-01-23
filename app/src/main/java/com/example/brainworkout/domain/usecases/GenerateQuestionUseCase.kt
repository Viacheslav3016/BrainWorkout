package com.example.brainworkout.domain.usecases

import com.example.brainworkout.domain.repository.GameRepository

class GenerateQuestionUseCase(private val repository: GameRepository) {

    operator fun invoke(maxSumValue:Int) {
        repository.GenerateQuestion(maxSumValue, MAX_COMPANION_SIZE)
    }


}
const val MAX_COMPANION_SIZE = 6