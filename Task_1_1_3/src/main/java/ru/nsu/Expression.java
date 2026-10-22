package ru.nsu;

/**
 * Абстрактный класс выражения от которого наследуются классы операций, переменных и чисел.
 */
public abstract class Expression {

    public void print() {
        System.out.println(this);
    }

    /**
     * Операция взятия производной.
     *
     * @param var переменная по которой вычисляется производная.
     * @return выражение с взятой производной.
     */
    public abstract Expression derivate(String var);

    /**
     * Вычисление Expression согласно переменным в памяти.
     *
     * @param vars Память, где записаны значения переменных.
     * @return вычисленное выражение.
     */
    public abstract int eval(Memory vars);
}
