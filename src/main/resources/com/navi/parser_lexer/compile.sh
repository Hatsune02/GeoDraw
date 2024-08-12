#! /bin/bash
echo "STARTING JFLEX COMPILING"
java -jar /home/dog/flexycup/jflex-full-1.9.1.jar -d ../../../../java/com/navi/backend/parser_lexer/ GeoLexer.flex

echo "STARTING CUP COMPILING"
java -jar /home/dog/flexycup/java-cup-11b.jar -parser GeoParser GeoParser.cup
mv GeoParser.java ../../../../java/com/navi/backend/parser_lexer/GeoParser.java
mv sym.java ../../../../java/com/navi/backend/parser_lexer/sym.java