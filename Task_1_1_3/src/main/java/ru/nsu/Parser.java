package ru.nsu;

/**
 * Класс парсера.
 */
public class Parser {
    private char[] exp;
    private int idx;


    /**
     * Парсер выражения.
     *
     * @param expression строка с выражением
     * @return Expression соответстующий выражению
     */
    public Expression parse(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new RuntimeException("Пустая строка");
        }
        this.exp = expression.trim().toCharArray();
        idx = 0;
        Expression res = parseExpression();

        skipSpaces();

        if (idx != exp.length) {
            throw new RuntimeException("Лишние символы после выражения");
        }
        return res;
    }


    private Expression parseExpression() {
        if (idx >= exp.length) {
            throw new RuntimeException("Неожиданный конец выражения");
        }
        if (exp[idx++] != '(') {
            throw new RuntimeException("something went wrong ( not found");
        }
        skipSpaces();
        // разбираем первый операнд
        Expression fst;
        if (idx >= exp.length) {
            throw new RuntimeException("Неожиданный конец выражения");
        }
        if (Character.isDigit(exp[idx])) {
            fst = parseNum();
        } else if (Character.isLetter(exp[idx])) {
            fst = parseVar();
        } else if (exp[idx] == '(') {
            fst = parseExpression();
        } else {
            throw new RuntimeException("something went wrong fst not found");
        }
        skipSpaces();

        if (idx >= exp.length) {
            throw new RuntimeException("Неожиданный конец выражения");
        }

        // оператор
        char operation;

        operation = exp[idx++];
        if (operation != '+'
                && operation != '-'
                && operation != '*'
                && operation != '/') {
            throw new RuntimeException("Недопустимая операция: " + operation);
        }

        skipSpaces();
        // разбираем второй операнд
        if (idx >= exp.length) {
            throw new RuntimeException("Неожиданный конец выражения");
        }
        Expression snd;
        if (Character.isDigit(exp[idx])) {
            snd = parseNum();
        } else if (Character.isLetter(exp[idx])) {
            snd = parseVar();
        } else if (exp[idx] == '(') {
            snd = parseExpression();
        } else {
            throw new RuntimeException("something went wrong snd not found");
        }
        skipSpaces();
        if (idx >= exp.length) {
            throw new RuntimeException("Неожиданный конец выражения");
        }
        if (exp[idx++] != ')') {
            throw new RuntimeException("something went wrong ) not found");
        }
        return switch (operation) {
            case '+' -> new Add(fst, snd);
            case '-' -> new Sub(fst, snd);
            case '*' -> new Mul(fst, snd);
            case '/' -> new Div(fst, snd);
            default -> throw new RuntimeException("invalid operand");
        };
    }


    private Expression parseVar() {
        StringBuilder sb = new StringBuilder();
        while (idx < exp.length && Character.isLetter(exp[idx])) {
            sb.append(exp[idx++]);
        }
        return new Variable(sb.toString());
    }


    private Expression parseNum() {
        int num = 0;
        while (idx < exp.length && Character.isDigit(exp[idx])) {
            num = num * 10 + (exp[idx++] - '0');
        }
        return new Number(num);
    }

    private void skipSpaces() {
        while (idx < exp.length && Character.isWhitespace(exp[idx])) {
            idx++;
        }
    }

}
