import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.byteme.fitness.di.initKoin
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

    AppTheme(
        isDarkTheme = isDarkTheme,
        dynamicColor = dynamicColor
    ) {


        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text("Byte Me")
                    },

                )
            }
        ) { paddings ->

            Box(modifier = Modifier.fillMaxSize().padding(paddings)) {
                Navigator(WelcomeScreen()) {
                    SlideTransition(it)
                }
            }
        }

    }
}