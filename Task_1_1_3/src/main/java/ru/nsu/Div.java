package ru.nsu;

public class Div extends Expression {
    Expression fst;
    Expression snd;

    public Div(Expression fst, Expression snd) {
        this.fst = fst;
        this.snd = snd;
    }

    @Override
    public Expression derivate(String var) {
        return new Div(
            new Sub(
                new Mul(fst.derivate(var), snd), new Mul(fst, snd.derivate(var))
            ),
            new Mul(snd, snd)
        );
    }

    @Override
    public int eval(Memory vars) {
        return fst.eval(vars) / snd.eval(vars);
    }

    @Override
    public String toString() {
        return "(" + fst.toString() + "/" + snd.toString() + ")";
    }
}

