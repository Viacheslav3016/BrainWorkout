package com.example.brainworkout.domain.usecases

import com.example.brainworkout.domain.entity.Level
import com.example.brainworkout.domain.repository.GameRepository

class GetGameSettingUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level){
        repository.GetGameSettings(level)
    }
}