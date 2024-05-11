package com.byteme.fitness.presentation.base

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

open class BaseViewModel<State, Effect>(initialState: State) : ScreenModel {

    val screenState = initialState

    private val _effect: MutableSharedFlow<Effect?> = MutableStateFlow(null)
    val effect: SharedFlow<Effect?> = _effect.asSharedFlow()

    suspend fun sendEffect(effect: Effect) {
        _effect.emit(effect)
    }
}