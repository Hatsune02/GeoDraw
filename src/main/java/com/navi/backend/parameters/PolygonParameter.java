package com.navi.backend.parameters;
import lombok.*;

@Getter @Setter
public class PolygonParameter extends Parameter{
    private double val4, val5;
    private String color;
    public PolygonParameter(int type, String id, double val1, double val2, double val3, double val4, double val5, String color) {
        super(type, id, val1, val2, val3, color);
        this.val4 = val4;
        this.val5 = val5;
        this.color = color;
    }

    @Override
    public void doAction() {
        super.doAction();
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "type=" + getType() +
                ", id='" + getId() + '\'' +
                ", val1=" + getVal1() +
                ", val2=" + getVal2() +
                ", val3=" + getVal3() +
                ", val3=" + val4 +
                ", val3=" + val5 +
                ", val3=" + color +
                '}';
    }
}
