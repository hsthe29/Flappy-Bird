package gameobject

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.mouseClickable
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class Game {
    var bird = mutableStateOf(Bird(15f))
    val currentPipes = mutableStateListOf<Pipe>()
    var passCount by mutableStateOf(0)
    var addFlags by mutableStateOf(false)
    var gameRunning by mutableStateOf(true)
    var bg by mutableStateOf(Background(0, 400))

    fun start(){
        currentPipes.add(Pipe((5..15).random() * 10))
    }

    fun updatePipe(){
        if (currentPipes.last().position < 750f) {
            currentPipes.add(Pipe((5..15).random() * 10))
            addFlags = true
        }
        if (currentPipes.first().position < -150)
            currentPipes.removeAt(0)
        currentPipes.asReversed().forEach { it.run(this) }
    }

    fun update(flags: Boolean) {
        if(flags)
            bird.value.update()
        else
            bird.value.fly()
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun FlappyBird() {
    val game = remember { Game() }
    var switchsFlagWindow by remember { mutableStateOf(1) }
    var upOrDown by remember { mutableStateOf(true) }
    var st by remember { mutableStateOf(System.currentTimeMillis()) }
    var end: Long

    Box(modifier = Modifier.fillMaxSize()
        .mouseClickable(
            onClick = {
            if(switchsFlagWindow == 2)
                upOrDown = !upOrDown
            st = System.currentTimeMillis()
            })) {
        Image(
            painter = painterResource("drawable/background.png"),
            contentDescription = "Icon",
            modifier = Modifier.fillMaxSize()
        )
        if (switchsFlagWindow == 1)
            Button(
                onClick = { switchsFlagWindow = 2
                          game.start()},
                modifier = Modifier.align(Alignment.Center).size(height = 68.dp, width = 150.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Image(
                    painter = painterResource("drawable/start.png"),
                    contentDescription = "Start",
                    modifier = Modifier.fillMaxSize()
                )
            }
        else if (switchsFlagWindow == 2){
            var text by remember { mutableStateOf("60 FPS") }
            var timeF by remember { mutableStateOf(System.nanoTime()) }
            game.currentPipes.forEach { PutPipe(it) }
            InitBird(game.bird.value)
//            Image(painter = painterResource("drawable/ground.png"),
//            contentDescription = "ground",
//            modifier = Modifier.offset(x = 0.dp, y = 400.dp))
            setBg(game.bg)
            Text(text = text,
            modifier = Modifier.background(color = Color.White))
            //PutPipe(Pipe(20))
            LaunchedEffect(Unit){
                while (game.bird.value.position <= 400f && game.gameRunning) {
                    withFrameNanos {
                        end = System.currentTimeMillis()
                        if (!upOrDown && end - st > 150) {
                            upOrDown = true
                        }
                        game.update(upOrDown)
                        game.updatePipe()
                        end = System.nanoTime()
                        if (end - timeF > 1000_000_000L) {
                            text = "${((2000_000_000L - (end - timeF)) % 1000000L / 10000).coerceAtLeast(30)} FPS"
                            timeF = System.nanoTime()
                        }
                    }
                    game.bg.render()
                }
                switchsFlagWindow = 3
            }
        }
        else {
            Image(painter = painterResource("drawable/gameover.png"),
            contentDescription = "gameover",
            Modifier.align(Alignment.Center))
            Text(text = "Score: ${game.passCount}",
            modifier = Modifier.align(Alignment.BottomCenter).padding(20.dp).background(Color.White).size(height = 30.dp, width = 80.dp),
            textAlign = TextAlign.Center)
        }
    }

}


