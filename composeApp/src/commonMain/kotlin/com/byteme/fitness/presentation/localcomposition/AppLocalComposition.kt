package com.byteme.fitness.presentation.localcomposition

import androidx.compose.runtime.staticCompositionLocalOf
import com.byteme.fitness.presentation.base.navigationbar.AppNavBarState
import com.byteme.fitness.presentation.base.topbar.AppTopBarState


val LocalTopBarState = staticCompositionLocalOf<AppTopBarState> {
    error("No AppTopBarState provided")
}

val LocalNavBarState = staticCompositionLocalOf<AppNavBarState> {
    error("No AppNavBarState provided")
}