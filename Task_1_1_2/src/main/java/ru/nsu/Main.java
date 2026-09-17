package ru.nsu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Game game = new Game(in, 1);
        while (true){
            System.out.println("--- ANOTHER ROUND ---");
            game.playRound();
        }
    }
}