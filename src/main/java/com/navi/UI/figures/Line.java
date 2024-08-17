package com.navi.UI.figures;

import java.awt.*;
import lombok.*;

@Setter @Getter
public class Line extends Figure {
    double x2, y2;
    public Line(int type, Shape shape, String name, String color, double x, double y) {
        super(type, shape, name, color, x, y);
    }
}
