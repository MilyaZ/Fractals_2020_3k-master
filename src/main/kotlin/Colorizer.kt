package ru.svm.gui.graphics

import java.awt.Color
import kotlin.math.*

//различные цветовые схемы

fun colorScheme1(x:Float): Color {
    val r = cos(2*sin(3*x)*cos(2*sin(3*x)))
    val g = abs(cos(10*x))
    val b = 1F - abs(cos(5*x))
    return Color(r,g,b)
}

fun colorScheme2(x:Float):Color{
    val r = abs(cos(12*x-6)*sin(x+10))
    val g = abs(sin(100*x*x -10*x)* cos(x))
    val b = abs(sin(1/(x+4)))
    return Color(r,g,b)
}

fun colorScheme3(x:Float):Color{
    val r = abs(cos(x-6)*sin(x+10))
    val g = abs(cos(200*x/15))
    val b = 1F - abs(sin(1/(x+4)))
    return Color(r,g,b)
}
fun colorScheme4(x:Float):Color{
    val r = abs(cos(9 - 100*x))
    val g = abs(cos(x*x)*sin(x*x*x))
    val b = abs(sin(x/(x+4)))
    return Color(r,g,b)
}

fun colorScheme5(x:Float):Color{
    val r = abs(cos(9 - 100*x))
    val g = abs(cos(x*x)*sin(x*x*x))
    val b = 1F - abs(sin(x/(x+4)))
    return Color(r,g,b)
}