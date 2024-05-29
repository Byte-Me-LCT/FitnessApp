package com.byteme.fitness.presentation.base.navigationbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator

class AppNavBarState {
    var navigator: Navigator? = null

    val height = 56.dp
    var needShowNavBar by mutableStateOf(false)
        private set

    fun hide(){ needShowNavBar = false }

    fun show(){ needShowNavBar = true }

    fun setIsNeedShowNavBar(needShowNavBar: Boolean) {
        this.needShowNavBar = needShowNavBar
    }

    fun setupNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

}

@Composable
fun rememberAppNavBarState() : AppNavBarState = remember { AppNavBarState() }