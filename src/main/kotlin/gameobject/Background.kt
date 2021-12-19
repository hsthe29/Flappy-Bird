package gameobject

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun setBg(bg : Background){
    Box(
        Modifier
            .offset(bg.position.dp, bg.y.dp)
            .shadow(0.dp)
            .clip(RectangleShape)
    ) {
        Image(painter = painterResource("drawable/ground.png"),
            contentDescription = "Pipe")
    }
}

data class Background(var x : Int, val y : Int){
    var position by mutableStateOf(x.toFloat())
    fun render(){
        position = if(position <= -2) 2f else position - 0.1f
    }
}