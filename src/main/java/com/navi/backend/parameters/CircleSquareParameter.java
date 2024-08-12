package com.navi.backend.parameters;
import lombok.*;

@Getter @Setter
public class CircleSquareParameter extends Parameter{
    private String color;
    public CircleSquareParameter(int type, String id, double val1, double val2, double val3, String color) {
        super(type, id, val1, val2, val3);
        this.color = color;
    }
}
