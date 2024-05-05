package com.byteme.fitness.presentation.screen.questionnaire

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class QuestionnaireState {
    val name: MutableState<String?> = mutableStateOf(null)
    val height: MutableState<String?> = mutableStateOf(null)
    val weight: MutableState<String?> = mutableStateOf(null)
}