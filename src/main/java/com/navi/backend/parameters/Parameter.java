package com.navi.backend.parameters;
import lombok.*;

@Setter @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Parameter {
    public static final int CIRCLE = 1;
    public static final int SQUARE = 2;
    public static final int RECTANGLE = 3;
    public static final int LINE = 4;
    public static final int POLYGON = 5;
    public static final int ANIMATE = 6;

    private int type;
    private String id;
    private double val1;
    private double val2;
    private double val3;
    private String color;
    public void doAction(){

    }

    @Override
    public String toString() {
        return "Parameter{" +
                "type=" + type +
                ", id='" + id + '\'' +
                ", val1=" + val1 +
                ", val2=" + val2 +
                ", val3=" + val3 +
                '}';
    }
}
