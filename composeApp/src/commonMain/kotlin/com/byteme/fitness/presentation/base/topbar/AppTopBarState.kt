package com.byteme.fitness.presentation.base.topbar

import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator

@Stable
class AppTopBarState {

    private var navigator: Navigator? = null

    var needShowTopBar by mutableStateOf(true)
    var title: String? by mutableStateOf(null)
    var isShowBackNavigate by mutableStateOf(false)
    var backgroundColor by mutableStateOf(Color.Transparent)
    var trailingContent: (@Composable () -> Unit)? by mutableStateOf(null)
    var iconBackColor by mutableStateOf(Color.White)
    var onBackClick: (() -> Unit)? by mutableStateOf(null)
    var titleAlignment: Alignment by mutableStateOf(Alignment.Center)
    var titleStyle: TextStyle? by mutableStateOf(null)
    var titleColor: Color by mutableStateOf(Color.Unspecified)
    var enterAnimation by mutableStateOf(fadeIn() + expandHorizontally())
    var exitAnimation by mutableStateOf(fadeOut() + shrinkHorizontally())
    val topBarHeight = 44.dp


    fun onBackClick() {
        onBackClick?.invoke() ?: navigator?.pop()
    }

    fun setupNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

}

@Composable
fun rememberAppTopBarState() : AppTopBarState {
    return remember {
        AppTopBarState()
    }
}