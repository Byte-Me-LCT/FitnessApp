package com.byteme.fitness.presentation.screen.statistics

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import com.byteme.fitness.presentation.navigation.Destinations

class StatisticsScreen(id: String) : Screen {

    override val key: ScreenKey = Destinations.STATISTICS

    @Composable
    override fun Content() {
        Text("STATISTICS")
    }

}