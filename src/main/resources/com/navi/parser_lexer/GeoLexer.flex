package com.navi.backend.flexycup;
import java_cup.runtime.*;
import static com.navi.backend.flexycup.sym.*;
import com.navi.backend.csv_controller.*;
import java.util.ArrayList;
%% //separador de area

%public
%class SqlLexer
%cup
%line
%column


LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

/* ___Operators___ */

Plus = [+]
Minus = [-]
Times = [*]
Division = [\/]
LParen = [\(]
RParen = [\)]

/* ___Reserved words___ */

    /* actions */
graficar = (graficar)
animar = (animar)
objeto = (objeto)
anterior = (anterior)

    /* colors */
blue = (azul)
red = (rojo)
yellow = (amarillo)
green = (verde)
sky = (celeste)
cyan = (cyan)
black = (negro)
pink = (rosa)
purple = (morado)

    /* animation */
line = (linea)
curve = (curva)

    /* objects */
circle = (circulo)
square = (cuadrado)
rectangle = (rectangulo)
polygon = (poligono)

/* Others */
Identifier = [a-zA-Z0-9-_]+
Digit = [0-9]+
Decimal = {Digit}\.{Digit}
Comma = [,]

%{
    public static ArrayList<TError> errors = new ArrayList<>();

    private Symbol symbol(int type){
        return new Symbol(type, yyline+1,yycolumn+1);
    }
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }
    private void error(){
        System.out.println("Error en linea: "+(yyline+1)+", columna: "+(yycolumn+1));
        TError err = new TError(yytext(), "Error Léxico", "Símbolo inválido", yyline+1, yycolumn+1);
        errors.add(err);
    }
%}

%%

{Comma}
{return symbol(COMMA, yytext());          }
{graficar}
{return symbol(GRAFICAR, yytext());          }
{animar}
{return symbol(ANIMAR, yytext());          }
{objeto}
{return symbol(OBJETO, yytext());          }
{anterior}
{return symbol(ANTERIOR, yytext());          }
{line}
{return symbol(LINE, yytext());          }
{curve}
{return symbol(CURVE, yytext());          }
{circle}
{return symbol(CIRCLE, yytext());          }
{square}
{return symbol(SQUARE, yytext());          }
{rectangle}
{return symbol(RECTANGLE, yytext());          }
{polygon}
{return symbol(POLYGON, yytext());          }

{Plus}
{return symbol(PLUS, yytext());}
{Minus}
{return symbol(MINUS, yytext());}
{Times}
{return symbol(TIMES, yytext());}
{Division}
{return symbol(DIVISION, yytext());}
{LParen}
{return symbol(LPAREN, yytext());}
{RParen}
{return symbol(RPAREN, yytext());}

{blue}
{return symbol(BLUE, yytext());}
{red}
{return symbol(RED, yytext());}
{yellow}
{return symbol(YELLOW, yytext());}
{green}
{return symbol(GREEN, yytext());}
{sky}
{return symbol(SKY, yytext());}
{cyan}
{return symbol(CYAN, yytext());}
{black}
{return symbol(BLACK, yytext());}
{pink}
{return symbol(PINK, yytext());}
{purple}
{return symbol(PURPLE, yytext());}


{Digit}
{return symbol(DIGIT, new Double(yytext()));}
{Decimal}
{return symbol(DIGIT, new Double(yytext()));}
{Identifier}
{return symbol(ID, yytext());}
{WhiteSpace} { /* ignore */ }

[\^´°¬|!$%&=?'¡¿\w]+
{Errors.getErrors().addLS(yyline+1, yycolumn+1, "Cadena no definida", yytext(), Errors.LEXICAL);}
[^]                 {error(); }


<<EOF>>             {return symbol(EOF); }