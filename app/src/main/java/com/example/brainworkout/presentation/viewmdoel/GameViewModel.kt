package com.example.brainworkout.presentation.viewmdoel

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brainworkout.R
import com.example.brainworkout.data.GenerateRepositoryImpl
import com.example.brainworkout.domain.entity.GameResult
import com.example.brainworkout.domain.entity.GameSettings
import com.example.brainworkout.domain.entity.Level
import com.example.brainworkout.domain.entity.Question
import com.example.brainworkout.domain.usecases.GenerateQuestionUseCase
import com.example.brainworkout.domain.usecases.GetGameSettingUseCase

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var gameSettings: GameSettings
    private lateinit var level: Level
    private var timer: CountDownTimer? = null
    private val repository = GenerateRepositoryImpl
    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingUseCase = GetGameSettingUseCase(repository)

    private val context = application

    private var countOfRightAnswers = 0
    private var countOfQuestion = 0

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private val _percentOfRightQuestion = MutableLiveData<Int>()
    val percentOfRightQuestion: LiveData<Int>
        get() = _percentOfRightQuestion

    private val _progressQuestions = MutableLiveData<String>()
    val progressQuestions: LiveData<String>
        get() = _progressQuestions

    private val _enoughCountOfRightAnswers = MutableLiveData<Boolean>()
    val enoughCountOfRightAnswers: LiveData<Boolean>
        get() = _enoughCountOfRightAnswers

    private val _enoughPercentOfRightAnswers = MutableLiveData<Boolean>()
    val enoughPercentOfRightAnswers: LiveData<Boolean>
        get() = _enoughPercentOfRightAnswers

    private val _minPercent = MutableLiveData<Int>()
    val minPercent: LiveData<Int>
        get() = _minPercent

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult


    fun gameStart(level: Level) {
        getGameSettings(level)
        startTimer()
        generateQuestion()

    }


    private fun chooseRightAnswer(number: Int) {
        val rightAnswer = question.value?.rightAnswer
        if (number == rightAnswer) {
            countOfRightAnswers++
        }
        countOfQuestion++
        updateProgress()
        generateQuestion()

    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(gameSettings.maxResult)
    }

    private fun updateProgress() {
        val percent = calculatePercentOfRightAnswers()
        _progressQuestions.value = String.format(
            context.resources.getString(R.string.number_right_answers),
            countOfRightAnswers,
            gameSettings.minCountOfRightAnswers
        )
        _enoughCountOfRightAnswers.value =
            countOfRightAnswers >= gameSettings.minCountOfRightAnswers
        _enoughPercentOfRightAnswers.value = percent >= gameSettings.minPercentOfRightAnswers
    }

    private fun calculatePercentOfRightAnswers(): Int {
        return ((countOfRightAnswers - countOfQuestion.toDouble()) * 100).toInt()
    }

    private fun getGameSettings(level: Level) {
        this.level = level
        this.gameSettings = getGameSettingUseCase(level)
        _minPercent.value = gameSettings.minPercentOfRightAnswers
    }

    private fun formateTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / SECONDS_IN_MINUTES
        val minutes = seconds / SECONDS_IN_MINUTES
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTES)
        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeSeconds * MILLIES_IN_SECONDS,
            MILLIES_IN_SECONDS
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formateTime(millisUntilFinished)
            }

            override fun onFinish() {
                finishGame()
            }
        }
        timer?.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }


    private fun finishGame() {
        _gameResult.value = GameResult(
            enoughCountOfRightAnswers.value == true && enoughPercentOfRightAnswers.value == true,
            countOfRightAnswers,
            countOfQuestion,
            gameSettings
        )
    }

    companion object {
        private const val SECONDS_IN_MINUTES = 60
        private const val MILLIES_IN_SECONDS = 1000L
    }
}