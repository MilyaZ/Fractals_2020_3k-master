import java.awt.Color
import java.awt.Graphics
import java.awt.Point

class MouseFramePainter(var g:Graphics) {
    var startPoint: Point? = null
    var currentPoint: Point? = null
        set(value) {
            paint()
            field = value
            paint()
        }
    var isVisible = false
        set(value) {
            field = value
            if (value){
                startPoint?.let { s ->
                    currentPoint?.let { c ->
                        g.setXORMode(Color.WHITE)
                        g.color = Color.BLACK
                        //для того чтобы прямоугольник рисовался во все стороны
                        if (s.x < c.x && s.y < c.y) {
                            g.drawRect(s.x, s.y, c.x - s.x, c.y - s.y)
                        }
                        if (s.x > c.x && s.y > c.y) {
                            g.drawRect(c.x, c.y, s.x - c.x, s.y - c.y)
                        }
                        if (s.x < c.x && s.y > c.y) {
                            g.drawRect(s.x, c.y, c.x - s.x, s.y - c.y)
                        }
                        if (s.x > c.x && s.y < c.y) {
                            g.drawRect(c.x, s.y, s.x - c.x, c.y - s.y)
                        }
                        g.setPaintMode()
                    }
                }

                currentPoint = null
                startPoint = null
                paint()
            }
        }

    private fun paint(){
        if (isVisible){
            startPoint?.let{s ->
                currentPoint?.let {c ->
                    g.setXORMode(Color.WHITE)
                    g.color = Color.BLACK
                    //для того чтобы прямоугольник рисовался во все стороны
                    if (s.x < c.x && s.y < c.y)
                    { g.drawRect(s.x, s.y, c.x-s.x, c.y-s.y) }
                    if (s.x > c.x && s.y > c.y)
                    { g.drawRect(c.x, c.y, s.x - c.x, s.y - c.y) }
                    if (s.x < c.x && s.y > c.y)
                    { g.drawRect( s.x, c.y, c.x-s.x, s.y-c.y) }
                    if (s.x > c.x && s.y < c.y)
                    { g.drawRect(c.x, s.y, s.x-c.x,c.y-s.y ) }
                    g.setPaintMode()
                }
            }
        }
    }
}
