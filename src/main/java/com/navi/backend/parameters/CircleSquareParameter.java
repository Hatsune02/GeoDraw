package com.navi.backend.parameters;
import com.navi.backend.parser_lexer.Expression;
import lombok.*;

@Getter @Setter
public class CircleSquareParameter extends Parameter{
    private String color;
    public CircleSquareParameter(int type, String id, Expression val1, Expression val2, Expression val3, String color) {
        super(type, id, val1, val2, val3, color);
        this.color = color;
    }
}
