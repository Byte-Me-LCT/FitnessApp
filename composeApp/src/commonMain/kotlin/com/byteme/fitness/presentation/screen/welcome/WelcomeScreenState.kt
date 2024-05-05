package com.byteme.fitness.presentation.screen.welcome

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class WelcomeScreenState {
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    val title: MutableState<String?> = mutableStateOf(null)
}