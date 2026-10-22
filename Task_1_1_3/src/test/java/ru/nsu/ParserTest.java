package ru.nsu;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Test;

class ParserTest {
    Parser parser = new Parser();

    @Test
    void ParseAddition() {
        Expression result = parser.parse("(1+2)");
        assertInstanceOf(Add.class, result);
    }

    @Test
    void additionWithVariables() {
        Expression result = parser.parse("(x+y)");

        assertNotNull(result);
        assertInstanceOf(Add.class, result);
    }

    @Test
    void subtractionWithVariables() {
        Expression result = parser.parse("(x-y)");

        assertNotNull(result);
        assertInstanceOf(Sub.class, result);
    }

    @Test
    void nestedExpressionWithVariables() {
        Expression result = parser.parse("((x+y)*z)");

        assertNotNull(result);
        assertInstanceOf(Mul.class, result);
    }

    @Test
    void multiplicationWithVariables() {
        Expression result = parser.parse("(x*y)");

        assertNotNull(result);
        assertInstanceOf(Mul.class, result);
    }

    @Test
    void divisionWithVariables() {
        Expression result = parser.parse("(x/y)");

        assertNotNull(result);
        assertInstanceOf(Div.class, result);
    }

    @Test
    void nestedExpression() {
        Expression result = parser.parse("((1+2)*3)");
        assertInstanceOf(Mul.class, result);
    }

    @Test
    void expressionWithSpaces() {
        Expression result = parser.parse("( 1 + ( 2 * 3 ) )");
        assertInstanceOf(Add.class, result);
    }
}