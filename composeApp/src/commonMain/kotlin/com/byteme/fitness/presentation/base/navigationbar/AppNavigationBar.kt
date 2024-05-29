package com.byteme.fitness.presentation.base.navigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import com.byteme.fitness.presentation.navigation.Destinations
import com.byteme.fitness.presentation.screen.questionnaire.QuestionnaireScreen
import com.byteme.fitness.presentation.screen.statistics.StatisticsScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun AppNavigationBar(
    navBarState: AppNavBarState,
) {
    val navigator = navBarState.navigator
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .navigationBarsPadding()
            .height(navBarState.height),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DefaultNavItem(
            selected = navigator?.lastItem?.key == Destinations.QUESTIONNAIRE
        ) {
           navigator?.replace(QuestionnaireScreen())
        }
        DefaultNavItem(
            selected = navigator?.lastItem?.key == Destinations.STATISTICS
        ) {
            navigator?.replace(StatisticsScreen("123123"))
        }
        DefaultNavItem {  }
        DefaultNavItem {  }

    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun RowScope.DefaultNavItem(
    iconId: Int = 0,
    selectedIconId: Int = 0,
    selected: Boolean = false,
    action: () -> Unit
) {
    Box(
        modifier = Modifier
            .topBorder(strokeWidth = 2.dp, color = MaterialTheme.colorScheme.primary, selected = selected)
            .fillMaxHeight()
            .weight(weight = 1f, fill = true)
            .clip(CircleShape)
            .clickable(selected.not()) { action() }
    ) {

        Icon(
            modifier = Modifier.align(Alignment.Center),
            imageVector = Icons.Default.Home,
            tint = if (selected) Color.LightGray else Color.DarkGray,
            contentDescription = null
        )

    }
}

@Composable
private fun Modifier.topBorder(strokeWidth: Dp, color: Color, selected: Boolean): Modifier {
    if (!selected) return this

    val density = LocalDensity.current
    val strokeWidthPx = density.run { strokeWidth.toPx() }

    return this then Modifier.drawBehind {
        val width = size.width

        drawLine(
            color = color,
            start = Offset(x = width * 0.2f, y = strokeWidthPx / 2),
            end = Offset(x = width * 0.8f , y = strokeWidthPx / 2),
            strokeWidth = strokeWidthPx
        )
    }
}
