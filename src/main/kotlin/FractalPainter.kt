package ru.svm.gui.graphics

import ru.svm.gui.graphics.convertation.CartesianScreenPlane
import ru.svm.gui.graphics.convertation.Converter
import ru.svm.math.Complex
import ru.svm.math.fractals.Mandelbrot
import java.awt.Color
import java.awt.Graphics
import kotlin.math.abs
import kotlin.math.max

class FractalPainter(val plane: CartesianScreenPlane) : Painter{

    //переменная функционального типа, определяет, принадлежит ли комплексное число
    //нужной области, вазвращает 1F если да
    var fractalTest: ((Complex)->Float)?=null
    //функциональная переменная для получения цвета
    var getColor:((Float)->Color) = {x->Color(x,x,x)}
    /**
     * Рисование фрактала
     * @param g графический контекст для рисования
     */
    override fun paint(g: Graphics?) {
        if (fractalTest==null || g == null) return
        //проверяем каждый пиксель области для рисования
        for(i in 0..plane.width){
            for (j in 0..plane.height){
                //проверка принадлежности к фракталу
                val r =fractalTest?.invoke(
                        Complex(
                        Converter.xScr2Crt(i,plane),
                        Converter.yScr2Crt(j, plane)
                        )
                )?:return
                //присвоение цвета
                g.color = if (r eq 1F )Color.BLACK else getColor(r)
                //заполнение пикселя цветом
                g.fillRect(i,j,1,1)
            }
        }
    }
    //для сравнения вещественных чисел с 0
    private infix fun Float.eq(other: Float) =
            abs(this - other) < max(Math.ulp(this), Math.ulp(other)) * 2
    private infix fun Float.neq(other: Float) =
            abs(this - other) > max(Math.ulp(this), Math.ulp(other)) * 2
}