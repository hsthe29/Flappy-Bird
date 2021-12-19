package gameobject

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun InitBird(bird: Bird) {
    Box(
        Modifier
            .offset(100.dp, bird.position.dp)
            .shadow(30.dp)
            .clip(CircleShape)
    ) {
        Image(painter = painterResource("drawable/img.png"),
        contentDescription = "Bird",
        modifier = Modifier.size(30.dp, 30.dp))
    }
}

data class Bird(val velocity : Float){
    var position by mutableStateOf(60.0f)
    fun fly() {
        if(position < 5)
            position = 0f
        else position -= 3.5f
    }
    fun update(){
        if (position < 432f) position += 1.5f
        else position = 432f
    }

    fun collision(x : Float, y : Float){

    }
}