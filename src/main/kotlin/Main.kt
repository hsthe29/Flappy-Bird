// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import gameobject.FlappyBird

@Preview
@OptIn(ExperimentalFoundationApi::class)
fun main() = application {
    Window(onCloseRequest = ::exitApplication,
    title = "Flappy Bird",
    state = rememberWindowState(height = 504.dp, width = 852.dp),
    icon =  painterResource("appIcon/flappybird.png")) {

        Box(modifier = Modifier.fillMaxWidth(1.0f)
            .fillMaxHeight(1.0f)
            ) {
                FlappyBird()
        }
    }
}


