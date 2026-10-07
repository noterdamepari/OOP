package ru.nsu;

/**
 * Main class.
 */
public class Main {
    /**
     * Entrypoint.
     */
    public static void main(String[] args) {
        Parser prs = new Parser();
        Expression exp = prs.parse("( 5 *     (    x    +    2     ))");
        Memory mem = new Memory("x=2");
        System.out.println(exp.eval(mem));
    }
}