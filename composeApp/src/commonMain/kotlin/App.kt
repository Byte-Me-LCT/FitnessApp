import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.byteme.fitness.di.initKoin
import com.byteme.fitness.presentation.base.navigationbar.AppNavigationBar
import com.byteme.fitness.presentation.base.navigationbar.rememberAppNavBarState
import com.byteme.fitness.presentation.base.topbar.rememberAppTopBarState
import com.byteme.fitness.presentation.localcomposition.LocalNavBarState
import com.byteme.fitness.presentation.localcomposition.LocalTopBarState
import com.byteme.fitness.presentation.screen.welcome.WelcomeScreen
import com.byteme.fitness.presentation.theme.AppTheme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(
    isDarkTheme: Boolean,
    dynamicColor: Boolean,
) {
    initKoin()

    val topBarState = rememberAppTopBarState()
    val navBarState = rememberAppNavBarState()

    AppTheme(
        isDarkTheme = isDarkTheme,
        dynamicColor = dynamicColor
    ) {
        CompositionLocalProvider(
            LocalTopBarState provides topBarState,
            LocalNavBarState provides navBarState,
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    AnimatedVisibility(
                        visible = topBarState.needShowTopBar
                    ) {

                        TopAppBar(
                            title = {
                                topBarState.title?.also {
                                    Text(text = it)
                                }
                            },
                            navigationIcon = {
                                if (topBarState.isShowBackNavigate) {
                                    IconButton(
                                        onClick = { topBarState.onBackClick() },

                                        content = {
                                            Image(
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "back",
                                                colorFilter = ColorFilter.tint(Color.White)
                                            )
                                        }
                                    )

                                }
                            }

                        )
                    }
                },
                bottomBar = {
                    AnimatedVisibility(
                        visible = navBarState.needShowNavBar
                    ) {
                        AppNavigationBar(navBarState)
                    }
                }
            ) { paddings ->


                Box(modifier = Modifier.fillMaxSize().padding(paddings)) {
                    Navigator(WelcomeScreen()) {
                        topBarState.setupNavigator(it)
                        navBarState.setupNavigator(it)
                        SlideTransition(it)
                    }
                }
            }
        }


    }
}