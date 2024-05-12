package com.byteme.fitness.core

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class StateField<T>(initialValue: T, initialState: State) {

    enum class State {
        LOADING,
        ERROR,
        USUAL;
    }


    var field: T by mutableStateOf(initialValue)
    var state: State by mutableStateOf(initialState)


}