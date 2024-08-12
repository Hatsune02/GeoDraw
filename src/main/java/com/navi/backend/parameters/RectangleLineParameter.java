package com.navi.backend.parameters;
import lombok.*;

@Getter @Setter
public class RectangleLineParameter extends Parameter{
    private double val4;
    private String color;
    public RectangleLineParameter(int type, String id, double val1, double val2, double val3, double val4, String color) {
        super(type, id, val1, val2, val3, color);
        this.val4 = val4;
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
                ", val3=" + color +
                '}';
    }
}
