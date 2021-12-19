package test

import kotlinx.coroutines.delay

data class D(var x: Int = 0){
    fun run(){
        print("${x++} ")
    }
}


fun main(){
    val x = System.nanoTime()
    suspend { delay(1000) }
    println(System.nanoTime() - x)
}