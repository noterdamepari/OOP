package ru.nsu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class DivTest {
    @Test
    void shouldEvaluateAddition() {
        Expression expression = new Div(
                new Number(10),
                new Number(5)
        );

        assertEquals(2, expression.eval(new Memory("")));
    }

    @Test
    void shouldEvaluateAdditionWithVariables() {
        Memory memory = new Memory("x=30; y=10");

        Expression expression = new Div(
                new Variable("x"),
                new Variable("y")
        );

        assertEquals(3, expression.eval(memory));
    }

    @Test
    void shouldReturnCorrectString() {
        Expression expression = new Div(
                new Variable("x"),
                new Number(5)
        );

        assertEquals("(x/5)", expression.toString());
    }


    @Test
    void shouldDerivateDivision() {
        Expression expression = new Div(
                new Variable("x"),
                new Number(2)
        );

        Expression derivative = expression.derivate("x");

        assertEquals("(((1*2)-(x*0))/(2*2))", derivative.toString());
    }

    @Test
    void shouldDerivateDivisionOfTwoVariables() {
        Expression expression = new Div(
                new Variable("x"),
                new Variable("y")
        );

        Expression derivative = expression.derivate("x");

        assertEquals(
                "(((1*y)-(x*0))/(y*y))",
                derivative.toString()
        );
    }
}
