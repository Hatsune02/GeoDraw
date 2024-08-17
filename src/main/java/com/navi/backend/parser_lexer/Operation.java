package com.navi.backend.parser_lexer;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Operation {
    private String sign;
    private int line;
    private int column;
    private String ex;
}
