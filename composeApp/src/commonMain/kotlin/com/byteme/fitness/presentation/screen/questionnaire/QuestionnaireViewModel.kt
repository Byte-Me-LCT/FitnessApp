package com.byteme.fitness.presentation.screen.questionnaire

import cafe.adriel.voyager.core.model.ScreenModel

class QuestionnaireViewModel : ScreenModel {

    val screenState = QuestionnaireState()

    fun changeName(name: String) {
        screenState.name.value = name
    }

    fun changeHeight(height: String) {
        screenState.height.value = height
    }

    fun changeWeight(weight: String) {
        screenState.weight.value = weight
    }
}