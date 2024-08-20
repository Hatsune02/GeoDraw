package com.navi.UI.figures;

import java.awt.*;
import lombok.*;

@Setter @Getter @ToString
public class Figure {
    private String type;
    private String stringColor;
    private String name;
    private Shape shape;
    private Color color;
    private double x, y;

    private boolean motion = false;
    private int motionType;
    private double finalX, finalY;
    private double position;

    public Figure (int type, Shape shape, String name, String color, double x, double y) {
        this.name = name;
        this.shape = shape;
        this.color = colorStringToColor(color);
        this.stringColor = color;
        switch (type){
            case 1:
                this.type = "Circulo";
                break;
            case 2:
                this.type = "Cuadrado";
                break;
            case 3:
                this.type = "Rectangulo";
                break;
            case 4:
                this.type = "Linea";
                break;
            case 5:
                this.type = "Poligono";
                break;
        }
        this.x = x;
        this.y = y;
    }

    public void addMotion(int motionType, double finalX, double finalY, double position) {
        this.motion = true;
        this.motionType = motionType;
        this.finalX = finalX;
        this.finalY = finalY;
        this.position = position;
    }

    private Color colorStringToColor(String str) {
        String colorHex = "";
        switch (str) {
            case "azul":
                colorHex = "#2209c3";
                break;
            case "rojo":
                colorHex = "#ff0000";
                break;
            case "amarillo":
                colorHex = "#ffc100";
                break;
            case "verde":
                colorHex = "#008000";
                break;
            case "celeste":
                colorHex = "#86cecb";
                break;
            case "cyan":
                colorHex = "#056d5b";
                break;
            case "negro":
                colorHex = "#000000";
                break;
            case "rosado":
                colorHex = "#ffaadd";
                break;
            case "morado":
                colorHex = "#4c00b0";
                break;
        }
        return Color.decode(colorHex);
    }
}
