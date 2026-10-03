package ru.nsu;

public class Variable extends Expression {
    private final String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public Expression derivate(String var) {
        return (var.equals(this.var)) ? new Number(1) : new Number(0);
    }

    @Override
    public int eval(Memory vars) {
        return vars.get(this.var);
    }

    @Override
    public String toString() {
        return this.var;
    }
}
