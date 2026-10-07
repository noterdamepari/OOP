package ru.nsu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class AddTest {
    @Test
    void shouldEvaluateAddition() {
        Expression expression = new Add(
                new Number(10),
                new Number(5)
        );

        assertEquals(15, expression.eval(new Memory("")));
    }

    @Test
    void shouldEvaluateAdditionWithVariables() {
        Memory memory = new Memory("x=10; y=20");

        Expression expression = new Add(
                new Variable("x"),
                new Variable("y")
        );

        assertEquals(30, expression.eval(memory));
    }

    @Test
    void shouldReturnCorrectString() {
        Expression expression = new Add(
                new Variable("x"),
                new Number(5)
        );

        assertEquals("(x+5)", expression.toString());
    }


    @Test
    void shouldDerivateAddition() {
        Expression expression = new Add(
                new Variable("x"),
                new Number(5)
        );

        Expression derivative = expression.derivate("x");

        assertEquals("(1+0)", derivative.toString());
    }

    @Test
    void shouldDerivateNestedExpression() {
        Expression expression = new Add(
                new Add(
                        new Variable("x"),
                        new Number(2)
                ),
                new Variable("x")
        );

        Expression derivative = expression.derivate("x");

        assertEquals("((1+0)+1)", derivative.toString());
    }

}