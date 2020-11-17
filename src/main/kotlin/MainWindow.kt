package ru.svm.gui

import MouseFramePainter
import ru.svm.gui.components.GraphicsPanel
import ru.svm.gui.graphics.*
import ru.svm.gui.graphics.colorScheme3
import ru.svm.gui.graphics.convertation.CartesianScreenPlane
import ru.svm.gui.graphics.convertation.Converter
import ru.svm.math.fractals.Mandelbrot
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.*
import java.lang.Math.max
import java.lang.Math.min
import javax.swing.GroupLayout
import javax.swing.JFrame

class MainWindow : JFrame(){

    private val minSize = Dimension(300, 200)
    private val mainPanel: GraphicsPanel
    init{
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "Построение множества Мандельброта"
        minimumSize = Dimension(700, 700)
        mainPanel = GraphicsPanel()
        mainPanel.background = Color.WHITE
        val gl = GroupLayout(contentPane)
        //var mfp = MouseFramePainter(mainPanel.graphics)

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addComponent(mainPanel, minSize.height, minSize.height, GroupLayout.DEFAULT_SIZE)
                .addGap(4)
        )

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(mainPanel, minSize.width, minSize.width, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(4))
        layout = gl

        pack()

        var plane = CartesianScreenPlane(
                mainPanel.width, mainPanel.height,
                -2.0, 1.0, -1.0, 1.0
        )
        var mfp = MouseFramePainter(mainPanel.graphics)
        var fp = FractalPainter(plane)
        val fractal = Mandelbrot()
        fp.fractalTest = fractal::isInSet
        fp.getColor = ::colorScheme4
        mainPanel.addPainter(fp)
        mainPanel.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                plane.realWidth = mainPanel.width
                plane.realHeight = mainPanel.height
                mfp = MouseFramePainter(mainPanel.graphics)
                mainPanel.repaint()
            }
        })

        //событие для нажатия/отпускания кнопки мыши
        mainPanel.addMouseListener(object: MouseAdapter(){
            override fun mousePressed(e: MouseEvent?) {
                repaint()
                e?.let {
                    mfp.isVisible = true
                    mfp.startPoint = it.point
                }
            }
            override fun mouseReleased(e: MouseEvent?) {
                mfp.isVisible = false
                e?.let{
                    mfp.currentPoint = it.point
                    mfp.startPoint?.let { s->
                        mfp.currentPoint?.let{c->
                            val xmin = min(c.x, s.x)
                            val xmax = max(c.x, s.x)
                            val ymax = min(c.y, s.y)
                            val ymin = max(c.y, s.y)

                            plane = CartesianScreenPlane(
                                    mainPanel.width, mainPanel.height,
                                    Converter.xScr2Crt(xmin, plane),
                                    Converter.xScr2Crt(xmax, plane),
                                    Converter.yScr2Crt(ymin, plane),
                                    Converter.yScr2Crt(ymax, plane),
                            )

                            mainPanel.removePainter(fp)
                            repaint()
                            fp = FractalPainter(plane)
                            fp.fractalTest = fractal::isInSet
                            fp.getColor = ::colorScheme4
                            mainPanel.addPainter(fp)
                            mainPanel.repaint()
                        }
                    }
                }
            }
        })
        //событие для движения мыши дааа это конечно да то что надо
        mainPanel.addMouseMotionListener(object : MouseAdapter(){
            override fun mouseDragged(e: MouseEvent?) {
                e?.let{
                    mfp.currentPoint = it.point
                }
            }

        })


    }
}