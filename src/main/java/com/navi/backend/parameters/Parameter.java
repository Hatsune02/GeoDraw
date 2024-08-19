package com.navi.backend.parameters;
import com.navi.backend.parser_lexer.Expression;
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
    private Expression val1;
    private Expression val2;
    private Expression val3;
    private String color;

    @Override
    public String toString() {
        return "Parameter{" +
                "type=" + type +
                ", id='" + id + '\'' +
                ", val1=" + val1.getValue() +
                ", val2=" + val2.getValue() +
                ", val3=" + val3.getValue() +
                '}';
    }
}
