package com.byteme.fitness.presentation.screen.questionnaire

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.aay.compose.barChart.BarChart
import com.aay.compose.barChart.model.BarParameters
import com.aay.compose.baseComponents.model.LegendPosition
import com.byteme.fitness.core.StateField
import fitnessapp.composeapp.generated.resources.Res
import fitnessapp.composeapp.generated.resources.height
import fitnessapp.composeapp.generated.resources.load
import fitnessapp.composeapp.generated.resources.name
import fitnessapp.composeapp.generated.resources.save
import fitnessapp.composeapp.generated.resources.weight
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

class QuestionnaireScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<QuestionnaireViewModel>()
        val screenState = viewModel.screenState

        val dialogState: MutableState<Pair<Boolean, String?>> = remember {
            mutableStateOf(false to null)
        }

        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                handleEffect(effect, dialogState)
            }
        }

        if (dialogState.value.first) {

            AlertDialog(
                onDismissRequest = {
                    dialogState.value = false to null
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            dialogState.value = false to null
                        },
                        content = {
                            Text(
                                text = "Ok"
                            )
                        }
                    )
                },
                text = {
                    dialogState.value.second?.also {
                        Text(
                            text = it
                        )
                    }
                }
            )
        }

        QuestionnaireContent(
            screenState = screenState,
            onNameChanged = viewModel::changeName,
            onHeightChanged = viewModel::changeHeight,
            onWeightChanged = viewModel::changeWeight,
            onSaveClicked = viewModel::validateAndSave,
            onLoadClicked = viewModel::loadAuth
        )
    }

    @Composable
    private fun QuestionnaireContent(
        screenState: QuestionnaireState,
        onNameChanged: (String) -> Unit,
        onHeightChanged: (String) -> Unit,
        onWeightChanged: (String) -> Unit,
        onSaveClicked: () -> Unit,
        onLoadClicked: () -> Unit,
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
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

            BarChartSample()


            Spacer(modifier = Modifier.weight(1f))

            SaveButton(
                onSaveClicked = onSaveClicked
            )

            LoadButton(
                onLoadClicked = onLoadClicked
            )
        }
    }

    @Composable
    fun BarChartSample() {

        val testBarParameters: List<BarParameters> = listOf(
            BarParameters(
                dataName = "Completed",
                data = listOf(0.6, 10.6, 80.0, 50.6, 44.0, 100.6, 10.0),
                barColor = Color(0xff6c5128)
            ),
            BarParameters(
                dataName = "Completed",
                data = listOf(50.0, 30.6, 77.0, 69.6, 50.0, 30.6, 80.0),
                barColor = Color(0xff4f9aba),
            ),
            BarParameters(
                dataName = "Completed",
                data = listOf(80.0, 99.6, 60.0, 80.6, 10.0, 100.6, 55.99),
                barColor = Color(0xff8bdf78),
            ),
        )

        Box(Modifier.fillMaxWidth().height(300.dp)) {
            BarChart(
                chartParameters = testBarParameters,
                gridColor = Color.DarkGray,
                xAxisData = listOf("2016", "2017", "2018", "2019", "2020", "2021", "2022"),
                isShowGrid = true,
                animateChart = true,
                showGridWithSpacer = true,
                yAxisStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                ),
                xAxisStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.W400
                ),
                yAxisRange = 15,
                barWidth = 20.dp,
                barCornerRadius = 16.dp,
                descriptionStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.White,
                ),
                legendPosition = LegendPosition.DISAPPEAR
            )
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun Name(
        name: StateField<String?>,
        onNameChanged: (String) -> Unit
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = name.field.orEmpty(),
            isError = name.state == StateField.State.ERROR,
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
        height: StateField<String?>,
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
            value = height.field.orEmpty(),
            isError = height.state == StateField.State.ERROR,
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

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun SaveButton(
        onSaveClicked: () -> Unit
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            onClick = onSaveClicked,
            content = {
                Text(
                    text = stringResource(Res.string.save)
                )
            }
        )
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun LoadButton(
        onLoadClicked: () -> Unit
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            onClick = onLoadClicked,
            content = {
                Text(
                    text = stringResource(Res.string.load)
                )
            }
        )
    }

    private fun handleEffect(effect: QuestionnaireEffect?, dialogState: MutableState<Pair<Boolean, String?>>) {
        when (effect) {
            is QuestionnaireEffect.Failed -> {
                dialogState.value = true to effect.message
            }
            QuestionnaireEffect.ProfileSaved -> {
                dialogState.value = true to "saved"
            }
            null -> {

            }
        }

    }
}