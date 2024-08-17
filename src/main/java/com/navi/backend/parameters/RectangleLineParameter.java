package com.navi.backend.parameters;
import com.navi.backend.parser_lexer.Expression;
import lombok.*;

@Getter @Setter
public class RectangleLineParameter extends Parameter{
    private Expression val4;
    private String color;
    public RectangleLineParameter(int type, String id, Expression val1, Expression val2, Expression val3, Expression val4, String color) {
        super(type, id, val1, val2, val3, color);
        this.val4 = val4;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "type=" + getType() +
                ", id='" + getId() + '\'' +
                ", val1=" + getVal1().getStr() +
                ", val2=" + getVal2().getStr() +
                ", val3=" + getVal3().getStr() +
                ", val3=" + val4.getStr() +
                ", val3=" + color +
                '}';
    }
}
