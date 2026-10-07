package ru.nsu;

/**
 * Класс умножения.
 */
public class Mul extends Expression {
    Expression fst;
    Expression snd;

    /**
     * Конструктор умножения.
     */
    public Mul(Expression fst, Expression snd) {
        this.fst = fst;
        this.snd = snd;
    }

    /**
     * Операция взятия производной.
     *
     * @param var переменная по которой вычисляется производная.
     * @return выражение с взятой производной.
     */
    @Override
    public Expression derivate(String var) {
        return new Add(
                new Mul(fst.derivate(var), snd), new Mul(fst, snd.derivate(var))
        );
    }

    /**
     * Вычисление Expression согласно переменным в памяти.
     *
     * @param vars Память, где записаны значения переменных.
     * @return вычисленное выражение.
     */
    @Override
    public int eval(Memory vars) {
        return fst.eval(vars) * snd.eval(vars);
    }

    /**
     * Преобразование Expression в строку.
     *
     * @return строка с выражением.
     */
    @Override
    public String toString() {
        return "(" + fst.toString() + "*" + snd.toString() + ")";
    }
}
