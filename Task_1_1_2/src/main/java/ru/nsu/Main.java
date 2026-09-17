package ru.nsu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Game game = new Game(in, 1);
        int playerScore = 0;
        int dealerScore = 0;

        while (true){
            System.out.println("--- ANOTHER ROUND ---");
            switch (game.playRound()) {
                case DEALER_WIN -> dealerScore++;
                case PLAYER_WIN -> playerScore++;
            }
            System.out.println("Счет " + playerScore + ":" + dealerScore);
            System.out.println("Для остановки введите 0, чтобы продолжить любой другой символ");
            int something = in.nextInt();
            if (something == 0){
                return;
            }
        }
    }
}