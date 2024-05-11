package com.byteme.fitness.presentation.screen.questionnaire

import cafe.adriel.voyager.core.model.screenModelScope
import com.byteme.fitness.core.utils.randomUUID
import com.byteme.fitness.domain.model.profile.Profile
import com.byteme.fitness.domain.repository.ProfileRepository
import com.byteme.fitness.domain.usecase.ProfileValidator
import com.byteme.fitness.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionnaireViewModel(
    private val profileRepository: ProfileRepository,
    private val profileValidator: ProfileValidator,
) : BaseViewModel<QuestionnaireState, QuestionnaireEffect>(QuestionnaireState()) {


    fun changeName(name: String) {
        screenState.name.field = name
    }

    fun changeHeight(height: String) {
        screenState.height.field = height
        try {
            height.toDouble()
            screenState.height.state = StateField.State.USUAL
        } catch (ex: Exception) {
            screenState.height.state = StateField.State.ERROR
        }
    }

    fun changeWeight(weight: String) {
        screenState.weight.value = weight
    }

    fun validateAndSave() {
        val name = screenState.name.field
        val height = screenState.height.field
        val weight = screenState.weight.value

        if (name.isNullOrEmpty() || height.isNullOrEmpty() || weight.isNullOrEmpty()) {
            screenModelScope.launch {
                sendEffect(QuestionnaireEffect.Failed("ERROR"))
            }
        } else {

            val result = profileValidator.validate(name = name, height = height, weight = weight)
            when (result) {
                ProfileValidator.State.VALID -> {
                    saveProfile(name, height, weight)
                }
                ProfileValidator.State.NAME_INCORRECT -> {

                }
                ProfileValidator.State.HEIGHT_INCORRECT -> {

                }
                ProfileValidator.State.WEIGHT_INCORRECT -> {

                }
            }
        }

    }

    private fun saveProfile(name: String, height: String, weight: String) {
        screenModelScope.launch {
            setSavingState()

            withContext(Dispatchers.IO) {
                val id = randomUUID()
                val profile = Profile(
                    id = id,
                    name = name,
                    height = height.toDouble(),
                    weight = weight.toDouble()
                )
                profileRepository.save(profile)
            }
                .onFailure {
                    it.printStackTrace()
                    setUsualState()
                    sendEffect(QuestionnaireEffect.Failed(it.message ?: "fail"))
                }
                .onSuccess {
                    setUsualState()
                    sendEffect(QuestionnaireEffect.ProfileSaved)
                }
        }
    }

    private fun setSavingState() {
        screenState.isSaving.value = true
    }

    private fun setUsualState() {
        screenState.isSaving.value = false
    }
}