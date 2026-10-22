package ru.nsu;

/**
 * Класс числа.
 */
public class Number extends Expression {
    private final int number;

    /**
     * Конструктор числа.
     */
    public Number(int num) {
        this.number = num;
    }


    /**
     * Операция взятия производной.
     *
     * @param var переменная по которой вычисляется производная.
     * @return число 0, т.к. от константы производная равняется нулю.
     */
    @Override
    public Expression derivate(String var) {
        return new Number(0);
    }

    /**
     * Вычисление Expression согласно переменным в памяти.
     *
     * @param vars Память, где записаны значения переменных.
     * @return вычисленное выражение (тут же это просто число).
     */
    @Override
    public int eval(Memory vars) {
        return this.number;
    }

    /**
     * Преобразование Number в строку.
     *
     * @return строка с числом.
     */
    @Override
    public String toString() {
        return String.valueOf(this.number);
    }
}
