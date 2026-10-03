package ru.nsu;

public class Mul extends Expression {
    Expression fst;
    Expression snd;

    public Mul(Expression fst, Expression snd) {
        this.fst = fst;
        this.snd = snd;
    }

    @Override
    public Expression derivate(String var) {
        return new Add(
                new Mul(fst.derivate(var), snd), new Mul(fst, snd.derivate(var))
        );
    }

    @Override
    public int eval(Memory vars) {
        return fst.eval(vars) * snd.eval(vars);
    }

    @Override
    public String toString(){
        return "(" + fst.toString() + "*" + snd.toString() + ")";
    }
}
