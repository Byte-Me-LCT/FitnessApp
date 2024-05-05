package com.byteme.fitness.presentation.screen.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.byteme.fitness.presentation.screen.questionnaire.QuestionnaireScreen
import fitnessapp.composeapp.generated.resources.Res
import fitnessapp.composeapp.generated.resources.open
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

class WelcomeScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<WelcomeViewModel>()
        val screenState = viewModel.screenState
        val navigator = LocalNavigator.currentOrThrow

        WelcomeContent(
            screenState = screenState,
            onOpenClicked = {
                navigator.push(QuestionnaireScreen())
            }
        )
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun WelcomeContent(
        screenState: WelcomeScreenState,
        onOpenClicked: () -> Unit,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {

            if (screenState.isLoading.value) {
                LinearProgressIndicator()
            }

            AnimatedVisibility(
                visible = screenState.title.value != null,
                enter = slideInHorizontally { -it }
            ) {
                Text(text = screenState.title.value.orEmpty())
            }

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .height(56.dp),
                onClick = onOpenClicked,
                content = {
                    Text(
                        text = stringResource(Res.string.open)
                    )
                }
            )


        }
    }
}