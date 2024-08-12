package com.navi.UI;

import java.awt.*;
import lombok.*;

@Setter @Getter
public class Figure {
    private String name;
    private Shape shape;
    private Color color;

    public Figure (Shape shape, String name, String color) {
        this.name = name;
        this.shape = shape;
        this.color = Color.decode(color);
    }
}
