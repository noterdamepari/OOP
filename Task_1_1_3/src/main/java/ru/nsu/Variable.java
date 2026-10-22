package ru.nsu;

/**
 * Класс переменной.
 */
public class Variable extends Expression {
    private final String var;


    /**
     * Конструктор переменной.
     */
    public Variable(String var) {
        this.var = var;
    }

    /**
     * Операция взятия производной.
     *
     * @param var переменная по которой вычисляется производная.
     * @return выражение с взятой производной.
     */
    @Override
    public Expression derivate(String var) {
        return (var.equals(this.var)) ? new Number(1) : new Number(0);
    }

    /**
     * Вычисление Expression согласно переменным в памяти.
     *
     * @param vars Память, где записаны значения переменных.
     * @return вычисленное выражение (тут же это число соответсвующее значению переменной из памяти).
     */
    @Override
    public int eval(Memory vars) {
        return vars.get(this.var);
    }

    /**
     * Преобразование Variable в строку.
     *
     * @return строка с переменной.
     */
    @Override
    public String toString() {
        return this.var;
    }
}
