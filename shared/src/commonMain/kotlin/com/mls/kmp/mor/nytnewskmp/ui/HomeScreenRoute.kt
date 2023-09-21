package com.mls.kmp.mor.nytnewskmp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.mls.kmp.mor.nytnewskmp.library.MR
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import org.koin.compose.koinInject

class HomeScreenRoute : Screen {

    @Composable
    override fun Content() {
        val viewModel: HomeScreenViewModel = koinInject()
        val state by viewModel.state.collectAsState()
        HomeScreenContent(state = state, onButtonClick = viewModel::onButtonClick)
    }
}

@Composable
fun HomeScreenContent(
    state: HomeScreenState,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {}
) {
    Surface(modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {
            when (state) {
                is HomeScreenState.Loading -> Text(text = "Loading...")
                is HomeScreenState.Error -> Text(text = "Error!")
                is HomeScreenState.Success -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        //PNG image from resources
                        Image(
                            painterResource(MR.images.Hello),
                            contentDescription = "Hello image",
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        //SVG image from resources
                        Image(
                            painter = painterResource(MR.images.world_svg),
                            contentDescription = "Hello image",
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        //String from resources
                        Text(text = stringResource(MR.strings.hello_world) + " " + state.data.number)
                    }

                    Button(
                        onClick = onButtonClick,
                        modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
                    ) {
                        Text(text = stringResource(MR.strings.click_me))
                    }
                }
            }
        }
    }
}