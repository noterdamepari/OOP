package ru.nsu;

public class Number extends Expression{
    private final int number;

    public Number(int num){
        this.number = num;
    }

    @Override
    public Expression derivate(String var) {
        return new Number(0);
    }

    @Override
    public int eval(Memory vars) {
        return this.number;
    }

    @Override
    public String toString() {
        return String.valueOf(this.number);
    }
}
