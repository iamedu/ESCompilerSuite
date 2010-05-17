package automata;

import com.trolltech.qt.gui.QColor;
import com.trolltech.qt.gui.QPaintEvent;
import com.trolltech.qt.gui.QPainter;
import com.trolltech.qt.gui.QPolygon;
import com.trolltech.qt.gui.QWidget;
import elex.Automata;
import elex.Estado;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author iamedu
 */
public class AutomataWidget extends QWidget {

    private List<Estado> actuales;
    private List<Estado> raices;
    private int separationHeight;
    private int separationWidth;
    private int ellipseWidth;
    private int ellipseHeight;

    public AutomataWidget(QWidget parent) {
        super(parent);
        raices = new ArrayList<Estado>();
        separationWidth = 100;
        separationHeight = 60;
        ellipseWidth = 50;
        ellipseHeight = 40;
        clear();
    }

    public void setActuales(List<Estado> actuales) {
        this.actuales = actuales;
    }

    public void clear() {

        List<Estado> visitar = new ArrayList<Estado>();
        List<Estado> visitados = new ArrayList<Estado>();
        Estado actual;

        for (Estado raiz : raices) {
            visitar.add(raiz);

            while (visitar.size() > 0) {
                actual = visitar.remove(0);
                if (visitados.contains(actual)) {
                    continue;
                }

                actual.setY(-1000);
                actual.setX(-1000);


                visitados.add(actual);
                if (actual.getTransicion1() != null) {
                    visitar.add(actual.getTransicion1().getEstado());
                }
                if (actual.getTransicion2() != null) {
                    visitar.add(actual.getTransicion2().getEstado());
                }
            }
        }

        raices.clear();
        resize(0, 0);
    }

    public void addAutomata(Automata a) {
        List<Estado> visitados;
        Estado inicial;
        if (a == null) {
            return;
        }

        if (a.getInicialList().size() > 0) {
            inicial = a.getInicialList().get(0);
        } else {
            return;
        }

        raices.add(inicial);

        visitados = new ArrayList<Estado>();
        calcSeparation(inicial, visitados, 0);

        normalize(inicial);
        repaint();
    }

    private void normalize(Estado padre) {
        int maximoX = 0;
        int minimoY = 10000;
        int maximoY = 0;

        int currentX;
        int currentY;

        //Maximo X
        List<Estado> visitar = new ArrayList<Estado>();
        List<Estado> visitados = new ArrayList<Estado>();

        visitar.add(padre);
        Estado actual;

        while (visitar.size() > 0) {
            actual = visitar.remove(0);
            if (visitados.contains(actual)) {
                continue;
            }

            currentX = actual.getX();
            currentY = actual.getY();

            if (currentY < minimoY) {
                minimoY = currentY;
            }

            if (currentY > maximoY) {
                maximoY = currentY;
            }

            if (currentX > maximoX) {
                maximoX = currentX;
            }

            if (currentY < 0) {
                actual.setArriba(true);
            } else {
                actual.setArriba(false);
            }

            visitados.add(actual);
            if (actual.getTransicion1() != null) {
                visitar.add(actual.getTransicion1().getEstado());
            }
            if (actual.getTransicion2() != null) {
                visitar.add(actual.getTransicion2().getEstado());
            }
        }

        minimoY -= 50;

        visitar.clear();
        visitados.clear();

        visitar.add(padre);
        actual = null;

        int height = height();

        while (visitar.size() > 0) {
            actual = visitar.remove(0);
            if (visitados.contains(actual)) {
                continue;
            }

            actual.setY(actual.getY() - minimoY + ellipseHeight + height);
            actual.setX(actual.getX() - ellipseWidth / 2);

            visitados.add(actual);
            if (actual.getTransicion1() != null) {
                visitar.add(actual.getTransicion1().getEstado());
            }
            if (actual.getTransicion2() != null) {
                visitar.add(actual.getTransicion2().getEstado());
            }

            int widthTotal = maximoX + ellipseWidth;
            int heightTotal = (int) (maximoY / 1.5);

            if (width() > widthTotal) {
                widthTotal = width();
            }

            if (heightTotal == 0) {
                heightTotal += ellipseHeight;
            }



            resize(widthTotal, (int) (height() + heightTotal + 50));
        }

    }

    private void calcSeparation(Estado padre, List<Estado> visitados, int position) {
        int newPosition = 0;
        Estado comun;
        if (visitados.contains(padre)) {
            return;
        }

        if (padre.getPadre() == null) {
            padre.setX(separationWidth);
            padre.setY(0);
        } else {
            if (padre.getX() == -1000) {
                padre.setX(padre.getPadre().getX() + separationWidth);

            }
            if (padre.getY() == -1000) {
                padre.setY(padre.getPadre().getY() + separationHeight * position);
            }
        }



        visitados.add(padre);

        if (padre.getTransicion1() != null && padre.getTransicion2() != null) {
            comun = hijoComun(padre, padre.getTransicion1().getEstado(), null);
            if (esHijo(padre.getTransicion2().getEstado(), padre.getTransicion1().getEstado(), padre) != null/* ||
                    esHijo(padre, padre.getTransicion1().getEstado(), null) != null*/) {
                newPosition = 0;
                comun = hijoComun(padre.getTransicion1().getEstado(),
                        padre.getTransicion2().getEstado(),
                        padre);
                if (comun.getY() == -1000) {
                    comun.setY(padre.getY());
                }
            } else {
                System.out.println(padre.getId());
                newPosition = -1;
                comun = hijoComun(padre.getTransicion1().getEstado(),
                        padre.getTransicion2().getEstado(),
                        padre);
                if (comun.getY() == -1000) {
                    comun.setY(padre.getY());
                }
            }
        } else {
            newPosition = 0;
        }

        if (padre.getTransicion1() != null) {
            padre.getTransicion1().getEstado().setPadre(padre);
            calcSeparation(padre.getTransicion1().getEstado(), visitados, newPosition);
        }
        if (padre.getTransicion2() != null) {
            padre.getTransicion2().getEstado().setPadre(padre);
            if (newPosition != 0) {
                newPosition = 1;
            }
            calcSeparation(padre.getTransicion2().getEstado(), visitados, newPosition);
        }

    }

    public void addAutomatas(Collection<Automata> automatas) {
        for (Automata a : automatas) {
            addAutomata(a);
        }
    }

    public void addAutomatas(Automata[] automatas) {
        for (int i = 0; i < automatas.length; i++) {
            if (automatas[i] != null) {
                addAutomata(automatas[i]);
            }
        }
    }

    private Estado hijoComun(Estado hijo1, Estado hijo2, Estado restrict) {
        List<Estado> visitar = new ArrayList<Estado>();
        List<Estado> visitados = new ArrayList<Estado>();

        visitar.add(hijo1);
        Estado actual;

        while (visitar.size() > 0) {
            actual = visitar.remove(0);
            if (visitados.contains(actual)) {
                continue;
            }

            if (esHijo(actual, hijo2, restrict) != null) {
                return actual;
            }

            visitados.add(actual);
            if (actual.getTransicion1() != null) {
                visitar.add(actual.getTransicion1().getEstado());
            }
            if (actual.getTransicion2() != null) {
                visitar.add(actual.getTransicion2().getEstado());
            }
        }
        return null;
    }

    private Estado esHijo(Estado hijo, Estado padre, Estado restrict) {
        List<Estado> visitar = new ArrayList<Estado>();
        List<Estado> visitados = new ArrayList<Estado>();

        visitar.add(padre);
        Estado actual;

        if (restrict != null) {
            visitados.add(restrict);
        }

        while (visitar.size() > 0) {
            actual = visitar.remove(0);
            if (visitados.contains(actual)) {
                continue;
            }

            if (actual.getId() == hijo.getId()) {
                return actual;
            }

            visitados.add(actual);
            if (actual.getTransicion1() != null) {
                visitar.add(actual.getTransicion1().getEstado());
            }
            if (actual.getTransicion2() != null) {
                visitar.add(actual.getTransicion2().getEstado());
            }
        }
        return null;

    }

    @Override
    protected void paintEvent(QPaintEvent paintEvent) {
        QPainter painter = new QPainter(this);

        for (Estado e : raices) {
            paintAutomata(e, painter);
        }
    }

    private void paintAutomata(Estado e, QPainter painter) {
        List<Estado> visitar = new ArrayList<Estado>();
        List<Estado> visitados = new ArrayList<Estado>();

        visitar.add(e);
        Estado actual;

        painter.setPen(QColor.black);
        painter.setBrush(QColor.blue);

        while (visitar.size() > 0) {
            actual = visitar.remove(0);
            painter.setBrush(QColor.blue);
            
            if(actuales != null && actuales.contains(actual)) {
                painter.setBrush(QColor.red);
            }
            if (visitados.contains(actual)) {
                continue;
            }

            painter.drawEllipse(actual.getX(),
                    actual.getY(),
                    ellipseWidth,
                    ellipseHeight);

            painter.setPen(QColor.white);
            painter.drawText(actual.getX() + 20,
                    actual.getY() + ellipseHeight / 2,
                    String.valueOf(actual.getId()));
            painter.setPen(QColor.black);

            visitados.add(actual);
            if (actual.getTransicion1() != null) {
                visitar.add(actual.getTransicion1().getEstado());
                drawArrow(actual, actual.getTransicion1().getEstado(), painter,
                        actual.getTransicion1().getClave());
            }
            if (actual.getTransicion2() != null) {
                visitar.add(actual.getTransicion2().getEstado());
                drawArrow(actual, actual.getTransicion2().getEstado(), painter,
                        actual.getTransicion2().getClave());
            }

        }
    }

    public void drawArrow(Estado e1, Estado e2, QPainter painter, char clave) {
        String texto;
        int textoWidth = 0;
        if (clave == 0) {
            texto = "EPSILON";
            textoWidth = 0;
        } else {
            textoWidth = 20;
            texto = String.valueOf(clave);

        }

        if (Math.abs(e2.getX() - e1.getX()) <= separationWidth && e2.getX() > e1.getX()) {

            painter.drawText(e1.getX() + ellipseWidth + textoWidth,
                    e1.getY() + ellipseHeight / 2,
                    texto);

            if (e1.getY() == e2.getY()) {
                painter.drawLine(e1.getX() + ellipseWidth,
                        e1.getY() + ellipseHeight / 2,
                        e2.getX(),
                        e2.getY() + ellipseHeight / 2);
                QPolygon p = new QPolygon();
                p.add(e2.getX(), e2.getY() + ellipseHeight / 2);
                p.add(e2.getX() - 5, e2.getY() + ellipseHeight / 2 - 5);
                p.add(e2.getX() - 5, e2.getY() + ellipseHeight / 2 + 5);
                painter.drawPolygon(p);

            } else {
                if (e1.getY() > e2.getY()) {
                    painter.drawLine(e1.getX() + ellipseWidth / 2,
                            e1.getY(),
                            e2.getX() + ellipseWidth / 40,
                            e2.getY() + ellipseHeight / 2);
                    QPolygon p = new QPolygon();
                    p.add(e2.getX(), e2.getY() + ellipseHeight / 2);
                    p.add(e2.getX() - 5, e2.getY() + ellipseHeight / 2 - 5);
                    p.add(e2.getX() - 5, e2.getY() + ellipseHeight / 2 + 5);
                    painter.drawPolygon(p);
                } else {
                    painter.drawLine(e1.getX() + ellipseWidth,
                            e1.getY() + ellipseHeight / 2,
                            e2.getX() + ellipseWidth / 2,
                            e2.getY());

                    QPolygon p = new QPolygon();
                    p.add(e2.getX() + ellipseWidth / 2, e2.getY());
                    p.add(e2.getX() + ellipseWidth / 2 - 2, e2.getY() - 10);
                    p.add(e2.getX() + ellipseWidth / 2 - 10, e2.getY() - 2);
                    painter.drawPolygon(p);


                }
            }
        } else {
            painter.drawText(e1.getX() + ellipseWidth + textoWidth,
                    e2.getY() + ellipseHeight,
                    texto);

            if (e2.getX() > e1.getX()) {

                if (e1.isArriba()) {
                    int height = e1.getHeight() + 100;
                    painter.drawArc(e1.getX() + ellipseWidth / 2,
                            e1.getY() - height / 2,
                            (e2.getX() - e1.getX()),
                            height,
                            0 * 16,
                            180 * 16);
                    QPolygon p = new QPolygon();
                    p.add(e2.getX() + ellipseWidth / 2, e2.getY());
                    p.add(e2.getX() + ellipseWidth / 2 - 5, e2.getY() - 5);
                    p.add(e2.getX() + ellipseWidth / 2 + 5, e2.getY() - 5);
                    painter.drawPolygon(p);
                } else {

                    int height = e1.getHeight() + 100;



                    painter.drawArc(e1.getX() + ellipseWidth / 2,
                            e1.getY() - height / 2 + ellipseHeight,
                            (e2.getX() - e1.getX()),
                            height,
                            180 * 16,
                            180 * 16);
                    QPolygon p = new QPolygon();
                    p.add(e2.getX() + ellipseWidth / 2, e2.getY() + ellipseHeight);
                    p.add(e2.getX() + ellipseWidth / 2 - 5, e2.getY() + ellipseHeight + 5);
                    p.add(e2.getX() + ellipseWidth / 2 + 5, e2.getY() + ellipseHeight + 5);
                    painter.drawPolygon(p);
                    if (e1.getY() != e2.getY()) {
                        painter.drawLine(e2.getX() + ellipseWidth / 2,
                                e1.getY() + ellipseHeight,
                                e2.getX() + ellipseWidth / 2,
                                e2.getY() + ellipseHeight);
                    }
                }

            } else {

                if (e2.getY() == e1.getY()) {
                    int height = e1.getHeight() + 100;

                    if (Math.abs(e2.getX() - e1.getX()) <= separationWidth) {
                        height -= 100;
                    }


                    painter.drawArc(e1.getX() + ellipseWidth / 2,
                            e1.getY() - height / 2,
                            (e2.getX() - e1.getX()),
                            height,
                            0 * 16,
                            180 * 16);
                    QPolygon p = new QPolygon();
                    p.add(e2.getX() + ellipseWidth / 2, e2.getY());
                    p.add(e2.getX() + ellipseWidth / 2 - 5, e2.getY() - 5);
                    p.add(e2.getX() + ellipseWidth / 2 + 5, e2.getY() - 5);
                    painter.drawPolygon(p);
                } else {
                    painter.drawLine(e1.getX() + ellipseWidth / 2,
                            e1.getY(),
                            e2.getX() + ellipseWidth,
                            e2.getY() + ellipseHeight / 2 + 5);
                    QPolygon p = new QPolygon();
                    p.add(e2.getX() + ellipseWidth, e2.getY() + ellipseHeight / 2 + 5);
                    p.add(e2.getX() + ellipseWidth - 2, e2.getY() + ellipseHeight / 2 + 15);
                    p.add(e2.getX() + ellipseWidth + 10, e2.getY() + ellipseHeight / 2 + 10);
                    painter.drawPolygon(p);
                }
            }

        }
    }
}
