package com.byteme.fitness.domain.usecase

class ProfileValidator {

    enum class State {
        VALID,
        NAME_INCORRECT,
        HEIGHT_INCORRECT,
        WEIGHT_INCORRECT;
    }


    fun validate(name: String, height: String, weight: String): State {

        return when {
            isNameIncorrect(name) -> State.NAME_INCORRECT
            isHeightIncorrect(height) -> State.HEIGHT_INCORRECT
            isWeightIncorrect(weight) -> State.WEIGHT_INCORRECT
            else -> State.VALID
        }
    }

    private fun isNameIncorrect(name: String?): Boolean {
        return name.isNullOrEmpty()
    }

    private fun isHeightIncorrect(height: String?): Boolean {
        return try {
            height?.toDouble()
            false
        } catch (ex: Exception) {
            true
        }
    }

    private fun isWeightIncorrect(weight: String?): Boolean {
        return try {
            weight?.toDouble()
            false
        } catch (ex: Exception) {
            true
        }
    }


}