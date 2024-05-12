package com.byteme.fitness.di

import com.byteme.fitness.FitnessDatabase
import com.byteme.fitness.data.repository.AuthRepositoryImpl
import com.byteme.fitness.data.repository.ProfileRepositoryImpl
import com.byteme.fitness.domain.repository.AuthRepository
import com.byteme.fitness.domain.repository.ProfileRepository
import com.byteme.fitness.domain.usecase.ProfileValidator
import com.byteme.fitness.presentation.screen.questionnaire.QuestionnaireViewModel
import com.byteme.fitness.presentation.screen.welcome.WelcomeViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

private val viewModelsModule = module {

    factory {
        WelcomeViewModel()
    }

    factory {
        QuestionnaireViewModel(
            profileRepository = get(),
            profileValidator = get(),
            authRepository = get(),
        )
    }

}


private val dataModule = module {
    single<FitnessDatabase> {
        FitnessDatabase(driver = get())
    }

}

private val repositoryModule = module {
    single<ProfileRepository> {
        ProfileRepositoryImpl(
            database = get()
        )
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            dataStore = get()
        )
    }
}

private val useCaseModule = module {
    factory {
        ProfileValidator()
    }
}

fun initKoin() {

    startKoin {
        modules(
            viewModelsModule,
            dataModule,
            repositoryModule,
            useCaseModule,
            platformModule
        )
    }
}