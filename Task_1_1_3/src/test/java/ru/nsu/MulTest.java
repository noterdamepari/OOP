package ru.nsu;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class MulTest {
    @Test
    void shouldEvaluateMultiplication() {
        Expression expression = new Mul(
                new Number(5),
                new Number(10)
        );

        assertEquals(50, expression.eval(new Memory("")));
    }

    @Test
    void shouldEvaluateMultiplicationWithVariables() {
        Memory memory = new Memory("x=6; y=8");

        Expression expression = new Mul(
                new Variable("x"),
                new Variable("y")
        );

        assertEquals(48, expression.eval(memory));
    }

    @Test
    void shouldReturnCorrectString() {
        Expression expression = new Mul(
                new Variable("x"),
                new Number(5)
        );

        assertEquals("(x*5)", expression.toString());
    }

    @Test
    void shouldDerivateMultiplication() {
        Expression expression = new Mul(
                new Variable("x"),
                new Number(2)
        );

        Expression derivative = expression.derivate("x");

        assertEquals(
                "((1*2)+(x*0))",
                derivative.toString()
        );
    }

    @Test
    void shouldDerivateMultiplicationOfTwoVariables() {
        Expression expression = new Mul(
                new Variable("x"),
                new Variable("y")
        );

        Expression derivative = expression.derivate("x");

        assertEquals(
                "((1*y)+(x*0))",
                derivative.toString()
        );
    }
}
