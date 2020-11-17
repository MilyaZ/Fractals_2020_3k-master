package ru.svm.gui.components

import ru.svm.gui.graphics.Painter
import java.awt.Graphics
import javax.swing.JPanel

class GraphicsPanel : JPanel(){
    private val painters: MutableList<Painter> = mutableListOf()

    /**
     * Переопределенный метод для рисования
     * @param g графический контекст для рисования
     */
    override fun paint(g: Graphics?) {
        super.paint(g)
        painters.forEach { it.paint(g) }
    }

    /**
     * Функция добавления пэинтера в список
     * @param p пэинтер
     */
    fun addPainter(p: Painter){
        if (!painters.contains(p))
            painters.add(p)
    }

    /**
     * Функция удаления пэинтера из списка
     * @param p пэинтер
     */
    fun removePainter(p: Painter) {
        if (painters.contains(p))
            painters.remove(p)
    }

}