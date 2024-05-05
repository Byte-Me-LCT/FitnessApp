package com.byteme.fitness.presentation.screen.questionnaire

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import fitnessapp.composeapp.generated.resources.Res
import fitnessapp.composeapp.generated.resources.height
import fitnessapp.composeapp.generated.resources.name
import fitnessapp.composeapp.generated.resources.weight
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

class QuestionnaireScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<QuestionnaireViewModel>()
        val screenState = viewModel.screenState

        QuestionnaireContent(
            screenState = screenState,
            onNameChanged = viewModel::changeName,
            onHeightChanged = viewModel::changeHeight,
            onWeightChanged = viewModel::changeWeight
        )
    }

    @Composable
    private fun QuestionnaireContent(
        screenState: QuestionnaireState,
        onNameChanged: (String) -> Unit,
        onHeightChanged: (String) -> Unit,
        onWeightChanged: (String) -> Unit,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Name(
                name = screenState.name,
                onNameChanged = onNameChanged,
            )

            Height(
                height = screenState.height,
                onHeightChanged = onHeightChanged
            )

            Weight(
                weight = screenState.weight,
                onWeightChanged = onWeightChanged
            )
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun Name(
        name: State<String?>,
        onNameChanged: (String) -> Unit
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = name.value.orEmpty(),
            label = {
                Text(
                    text = stringResource(Res.string.name)
                )
            },
            onValueChange = onNameChanged
        )
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun Height(
        height: State<String?>,
        onHeightChanged: (String) -> Unit
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(Res.string.height)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            value = height.value.orEmpty(),
            onValueChange = onHeightChanged
        )
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun Weight(
        weight: State<String?>,
        onWeightChanged: (String) -> Unit
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(Res.string.weight)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            value = weight.value.orEmpty(),
            onValueChange = onWeightChanged
        )
    }
}