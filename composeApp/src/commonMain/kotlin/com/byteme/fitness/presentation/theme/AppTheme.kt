package com.byteme.fitness.presentation.theme

import androidx.compose.runtime.Composable

@Composable
expect fun AppTheme(
    isDarkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)