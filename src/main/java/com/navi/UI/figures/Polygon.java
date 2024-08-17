package com.navi.UI.figures;
import java.awt.*;
import lombok.*;

@Getter @Setter
public class Polygon extends Figure{
    private int sides;
    private double height, width;

    public Polygon(int type, Shape shape, String name, String color, double x, double y) {
        super(type, shape, name, color, x, y);
    }
}
