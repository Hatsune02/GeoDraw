package com.navi.UI;

import com.navi.UI.figures.Figure;
import com.navi.UI.figures.Line;
import com.navi.UI.figures.Polygon;
import com.navi.backend.parameters.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.*;

@Setter @Getter
public class Canvas extends JPanel implements MouseListener {
    private List<Figure> figures = new ArrayList<>();

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
                Path2D.Double polygon = createPolygon(x, y, sides, polygonWidth, polygonHeight);
                shape = polygon;
                break;
        }
        Figure fig;
        if(type == Parameter.POLYGON) {
            Polygon aux = new Polygon(type, shape, name, color, x, y);
            aux.setSides((int) params[2]);
            aux.setWidth(params[3]);
            aux.setHeight(params[4]);
            fig = aux;
        }
        else if(type == Parameter.LINE){
            Line aux = new Line(type, shape, name, color, x, y);
            aux.setX2(params[2]);
            aux.setY2(params[3]);
            fig = aux;
        }
        else{
            fig = new Figure(type, shape, name, color, x, y);
        }
        figures.add(fig);
    }

    private Path2D.Double createPolygon(double x, double y, double sides, double polygonWidth, double polygonHeight) {
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
        return polygon;
    }

    public void moveFigure(Figure figure, int duration) {
        Timer timer = new Timer(10, null);
        double x1 = figure.getShape().getBounds2D().getX();
        double y1 = figure.getShape().getBounds2D().getY();
        double x2 = figure.getFinalX() - x1; // Esto es x2 para movimiento linear
        double y2 = figure.getFinalY() - y1; // Esto es y2 para movimiento linear

        // control point for the curve
        double controlX = (x1 + figure.getFinalX()) / 2;
        double controlY = (y1 + figure.getFinalY()) / 2;

        // Desplazamiento perpendicular al vector que une (x1, y1) y (x2, y2)
        double dx = figure.getFinalX() - x1;
        double dy = figure.getFinalY() - y1;
        double perpOffset = 50;  // Ajustar para determinar la curvatura
        controlX += -dy / Math.sqrt(dx * dx + dy * dy) * perpOffset;
        controlY += dx / Math.sqrt(dx * dx + dy * dy) * perpOffset;

        double finalControlX = controlX;
        double finalControlY = controlY;

        long startTime = System.currentTimeMillis();

        timer.addActionListener(e -> {
            long elapsed = System.currentTimeMillis() - startTime;
            double t = Math.min(1.0, (double) elapsed / duration);

            double newX = 0;
            double newY = 0;

            if(figure.getMotionType() == 1){
                newX = x1 + t * x2;
                newY = y1 + t * y2;
            }
            else if(figure.getMotionType() == 2){
                // Curva de Bézier cuadrática: B(t) = (1-t)²P0 + 2(1-t)tP1 + t²P2
                newX = Math.pow(1 - t, 2) * x1 + 2 * (1 - t) * t * finalControlX + Math.pow(t, 2) * figure.getFinalX();
                newY = Math.pow(1 - t, 2) * y1 + 2 * (1 - t) * t * finalControlY + Math.pow(t, 2) * figure.getFinalY();
            }
            System.out.println("x: " + newX + " y: " + newY);

            if (figure.getShape() instanceof Rectangle2D) {
                Rectangle2D rect = (Rectangle2D) figure.getShape();
                figure.setShape(new Rectangle2D.Double(newX, newY, rect.getWidth(), rect.getHeight()));
            }
            else if (figure.getShape() instanceof Ellipse2D) {
                Ellipse2D ellipse = (Ellipse2D) figure.getShape();
                figure.setShape(new Ellipse2D.Double(newX, newY, ellipse.getWidth(), ellipse.getHeight()));
            }
            else if (figure.getShape() instanceof Line2D) {
                var originalLine = (Line) figure;
                double newX2 = originalLine.getX2() + t * x2;
                double newY2 = originalLine.getY2() + t * y2;
                figure.setShape(new Line2D.Double(newX, newY, newX2, newY2));
            }
            else if (figure.getShape() instanceof Path2D.Double) {
                var polygonOriginal = (Polygon) figure;
                var newPolygon = createPolygon(newX, newY, polygonOriginal.getSides(), polygonOriginal.getWidth(), polygonOriginal.getWidth());
                figure.setShape(newPolygon);
            }
            repaint();

            if (t >= 1.0) {
                timer.stop();

            }
        });

        timer.start();
        figure.setX(figure.getFinalX());
        figure.setY(figure.getFinalY());
    }

    public void addMotion(int motionType, double finalX, double finalY, double position) {
        if(!figures.isEmpty()) {
            figures.get(figures.size()-1).addMotion(motionType, finalX, finalY, position);
        }
    }

    public void setParameters(ArrayList<Parameter> parameters){
        for(Parameter parameter : parameters){
            switch (parameter.getType()) {
                case Parameter.CIRCLE, Parameter.SQUARE:
                    addFigure(parameter.getType(), parameter.getId(), parameter.getColor(),
                            parameter.getVal1().getValue(), parameter.getVal2().getValue(),
                            parameter.getVal3().getValue());
                    break;

                case Parameter.RECTANGLE, Parameter.LINE:
                    var p = (RectangleLineParameter) parameter;
                    addFigure(p.getType(), p.getId(), p.getColor(), p.getVal1().getValue(),
                            p.getVal2().getValue(), p.getVal3().getValue(), p.getVal4().getValue());
                    break;

                case Parameter.POLYGON:
                    var pP = (PolygonParameter) parameter;
                    addFigure(pP.getType(), pP.getId(), pP.getColor(), pP.getVal1().getValue(),
                            pP.getVal2().getValue(), pP.getVal3().getValue(), pP.getVal4().getValue(),
                            pP.getVal5().getValue());
                    break;

                case Parameter.ANIMATE:
                    int animationType = 0;
                    if(parameter.getId().equals("linea")) animationType = 1;
                    else if(parameter.getId().equals("curva")) animationType = 2;

                    addMotion(animationType, parameter.getVal1().getValue(),
                            parameter.getVal2().getValue(), parameter.getVal3().getValue());
                    break;
            }
        }

        repaint();
    }

    public void initMotions(){
        ArrayList<Figure> figuresMotion = figures.stream()
                .filter(Figure::isMotion) // filter figure by boolean motion true
                .sorted(Comparator.comparingDouble(Figure::getPosition)) // order from lowest to highest according to position
                .collect(Collectors.toCollection(ArrayList::new)); //

        for (int i = 0; i < figuresMotion.size(); i++) {
            int index = i;
            Timer timer = new Timer(i * 4500, new ActionListener() {  // time between moves
                @Override
                public void actionPerformed(ActionEvent e) {
                    var f = figuresMotion.get(index);
                    moveFigure(f, 4000);

                }
            });
            timer.setRepeats(false);  // no repeat
            timer.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //thickness for line
        float thickness = 2.5f;
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
        for (Figure figure : figures) {
            if (figure.getShape() instanceof Line2D) {
                Line2D line = (Line2D) figure.getShape();
                if (line.ptSegDist(clickPoint) <= 2.5f) {
                    showInfo(figure);
                    break;
                }
            }
            else if (figure.getShape().contains(clickPoint)) {
                showInfo(figure);
                break;
            }
        }
    }

    public void showInfo(Figure figure){
        String info = "Figura: " + figure.getType() +
                "\nNombre: " + figure.getName() +
                "\nPosicion: " + figure.getX() + ", " + figure.getY() +
                "\nColor: " + figure.getStringColor();

        JOptionPane.showMessageDialog(this, info, "Información de la Figura", JOptionPane.INFORMATION_MESSAGE);
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
