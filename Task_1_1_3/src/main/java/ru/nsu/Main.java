package ru.nsu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Parser prs = new Parser();
        Expression exp = prs.parse("( 5 *     (    x    +    2     ))");
        Memory mem = new Memory("x=2");
        System.out.println(exp.eval(mem));
    }
}