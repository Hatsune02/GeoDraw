package com.navi.UI;

import com.navi.backend.parameters.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

public class Canvas extends JPanel implements MouseListener {
    private final List<Figure> figures = new ArrayList<>();

    public Canvas(){
        addMouseListener(this);
    }
    public void addFigure(int type, String name, String color, double... params) {
        Shape shape = null;
        double x = params[0];
        double y = params[1];
        switch (type) {
            case Parameter.CIRCLE:
                double radius = params[2];
                shape = new Ellipse2D.Double(x, y, radius*2, radius*2);
                break;

            case Parameter.SQUARE:
                double side = params[2];
                shape = new Rectangle2D.Double(x, y, side, side);
                break;

            case Parameter.RECTANGLE:
                double width = params[2];
                double height = params[3];
                shape = new Rectangle2D.Double(x, y, width, height);
                break;

            case Parameter.LINE:


                double x2 = params[2];
                double y2 = params[3];
                shape = new Line2D.Double(x, y, x2, y2);
                break;

            case Parameter.POLYGON:
                int sides = (int) params[2];
                double polygonWidth = params[3];
                double polygonHeight = params[4];
                Path2D.Double polygon = new Path2D.Double();
                double angle = 2 * Math.PI / sides;

                double initialX = x + (polygonWidth / 2 * Math.cos(0));
                double initialY = y + (polygonHeight / 2 * Math.sin(0));
                polygon.moveTo(initialX, initialY);

                for (int i = 1; i < sides; i++) {
                    double xPoint = x + (polygonWidth / 2 * Math.cos(angle * i));
                    double yPoint = y + (polygonHeight / 2 * Math.sin(angle * i));
                    polygon.lineTo(xPoint, yPoint);
                }

                polygon.closePath();
                shape = polygon;
                break;
        }
        figures.add(new Figure(shape, name, color));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //thickness for line
        float thickness = 2.0f;
        g2d.setStroke(new BasicStroke(thickness));

        for(Figure figure : figures){
            g2d.setColor(figure.getColor());
            if(figure.getShape() instanceof Line2D.Double){
                g2d.draw(figure.getShape());
            }
            else{
                g2d.fill(figure.getShape());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickPoint = e.getPoint();

        // Verificar si el clic ocurrió dentro de alguna figura
        for (Figure figure : figures) {
            if (figure.getShape().contains(clickPoint)) {
                // Mostrar información sobre la figura clickeada
                String info = "Figura: " + figure.getName() + "\nPosicion: " +
                        figure.getShape().getBounds2D().getX() + ", " + figure.getShape().getBounds2D().getY();
                JOptionPane.showMessageDialog(this, info, "Información de la Figura", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
