package ru.nsu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SubTest {
    @Test
    void shouldEvaluateSubtraction() {
        Expression expression = new Sub(new Number(10), new Number(4));

        assertEquals(6, expression.eval(new Memory("")));
    }

    @Test
    void shouldEvaluateSubtractionWithVariables() {
        Memory memory = new Memory("x=20; y=7");
        Expression expression = new Sub(new Variable("x"), new Variable("y"));

        assertEquals(13, expression.eval(memory));
    }

    @Test
    void shouldReturnCorrectString() {
        Expression expression = new Sub(new Variable("x"), new Number(5));

        assertEquals("(x-5)", expression.toString());
    }

    @Test
    void shouldDerivateSubtraction() {
        Expression expression = new Sub(new Variable("x"), new Number(5));

        Expression derivative = expression.derivate("x");

        assertEquals("(1-0)", derivative.toString());
    }

    @Test
    void shouldDerivateSubtractionOfTwoVariables() {
        Expression expression = new Sub(
                new Variable("x"),
                new Variable("y")
        );

        Expression derivative = expression.derivate("x");

        assertEquals("(1-0)", derivative.toString());
    }
}
