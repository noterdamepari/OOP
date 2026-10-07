package ru.nsu;

public abstract class Expression {

    public void print() {
        System.out.println(this);
    }

    public abstract Expression derivate(String var);

    public abstract int eval(Memory vars);
}
