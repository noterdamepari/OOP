package ru.nsu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserExceptionsTest {
    Parser parser = new Parser();

    @Test
    void nullExpression() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse(null)
        );

        assertEquals("Пустая строка", exception.getMessage());
    }

    @Test
    void expressionContainingOnlySpaces() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("     ")
        );

        assertEquals("Пустая строка", exception.getMessage());
    }

    @Test
    void openingParenthesisIsMissing() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("1+2")
        );

        assertEquals(
                "something went wrong ( not found",
                exception.getMessage()
        );
    }

    @Test
    void unexpectedEndAfterOpeningParenthesis() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("(")
        );

        assertEquals(
                "Неожиданный конец выражения",
                exception.getMessage()
        );
    }

    @Test
    void firstOperandIsMissing() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("(+2)")
        );

        assertEquals(
                "something went wrong fst not found",
                exception.getMessage()
        );
    }

    @Test
    void secondOperandIsMissing() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("(1+)")
        );

        assertEquals(
                "something went wrong snd not found",
                exception.getMessage()
        );
    }

    @Test
    void operatorIsMissing() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("(12)")
        );

        assertEquals(
                "Недопустимая операция: )",
                exception.getMessage()
        );
    }

    @Test
    void invalidOperator() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("(1%2)")
        );

        assertEquals(
                "Недопустимая операция: %",
                exception.getMessage()
        );
    }

    @Test
    void closingParenthesisIsMissing() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("(1+2")
        );

        assertEquals(
                "Неожиданный конец выражения",
                exception.getMessage()
        );
    }

    @Test
    void closingParenthesisIsWrong() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("(1+2]")
        );

        assertEquals(
                "something went wrong ) not found",
                exception.getMessage()
        );
    }

    @Test
    void emptyParentheses() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("()")
        );

        assertEquals(
                "something went wrong fst not found",
                exception.getMessage()
        );
    }

    @Test
    void charactersAfterExpression() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("(1+2)abc")
        );

        assertEquals(
                "Лишние символы после выражения",
                exception.getMessage()
        );
    }

    @Test
    void missingNestedExpression() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("((1+2)+)")
        );

        assertEquals(
                "something went wrong snd not found",
                exception.getMessage()
        );
    }

    @Test
    void invalidFirstOperand() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("(# + 2)")
        );

        assertEquals(
                "something went wrong fst not found",
                exception.getMessage()
        );
    }

    @Test
    void invalidSecondOperand() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse("(1+#)")
        );

        assertEquals(
                "something went wrong snd not found",
                exception.getMessage()
        );
    }
}
