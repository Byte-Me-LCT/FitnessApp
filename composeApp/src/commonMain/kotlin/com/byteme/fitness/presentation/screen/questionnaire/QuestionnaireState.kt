package com.byteme.fitness.presentation.screen.questionnaire

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

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

class StateField<T>(initialValue: T, initialState: State) {

    enum class State {
        LOADING,
        ERROR,
        USUAL;
    }


    var field: T by mutableStateOf(initialValue)
    var state: State by mutableStateOf(initialState)


}