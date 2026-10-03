package ru.nsu;

public class Sub extends Expression {
    Expression fst;
    Expression snd;

    Sub(Expression fst, Expression snd) {
        this.fst = fst;
        this.snd = snd;
    }

    @Override
    public Expression derivate(String var) {
        return new Add(fst.derivate(var), snd.derivate(var));
    }

    @Override
    public int eval(Memory vars) {
        return fst.eval(vars) - snd.eval(vars);
    }

    @Override
    public String toString() {
        return "(" + fst.toString() + "-" + snd.toString() + ")";
    }
}
