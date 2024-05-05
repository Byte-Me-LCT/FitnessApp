package com.byteme.fitness.di

import com.byteme.fitness.presentation.screen.questionnaire.QuestionnaireViewModel
import com.byteme.fitness.presentation.screen.welcome.WelcomeViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

private val viewModelsModule = module {

    factory {
        WelcomeViewModel()
    }

    factory {
        QuestionnaireViewModel()
    }

}


private val dataModule = module {
    single {

    }

}

private val repositoryModule = module {

}

fun initKoin() {

    startKoin {
        modules(viewModelsModule, dataModule, repositoryModule, sqlDriverModule)
    }
}