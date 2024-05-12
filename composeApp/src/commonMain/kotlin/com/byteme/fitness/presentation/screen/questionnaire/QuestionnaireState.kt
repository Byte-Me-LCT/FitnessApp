package com.byteme.fitness.presentation.screen.questionnaire

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.byteme.fitness.core.StateField

class QuestionnaireState {
    val name: StateField<String?> = StateField(null, StateField.State.USUAL)
    val height: StateField<String?> = StateField(null, StateField.State.USUAL)
    val weight: MutableState<String?> = mutableStateOf(null)
    val isSaving: MutableState<Boolean> = mutableStateOf(false)
}

sealed class QuestionnaireEffect {
    data object ProfileSaved : QuestionnaireEffect()
    data class Failed(val message: String): QuestionnaireEffect()
}
