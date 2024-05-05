package com.byteme.fitness.presentation.screen.welcome

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeViewModel : ScreenModel {

    val screenState = WelcomeScreenState()

    init {
        screenModelScope.launch {
            setLoadingState()
            delay(2000)
            setUsualState()
            screenState.title.value = "Welcome to Fitness"
        }
    }

    private fun setLoadingState() {
        screenState.isLoading.value = true
    }

    private fun setUsualState() {
        screenState.isLoading.value = false
    }
}