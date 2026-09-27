package ru.nsu;

import java.util.List;
import java.util.Scanner;

/**
 * Main class.
 */
public class Main {

    /**
     * Program entrypoint.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int playerScore = 0;
        int dealerScore = 0;
        int roundCnt = 0;

        System.out.println("Добро пожаловать в Блэкджек!");
        while (true) {
            Deck deck = new Deck(1);
            Game game = new Game(in, deck);
            System.out.printf("\n\nРаунд %d\n", ++roundCnt);
            switch (game.playRound()) {
                case DEALER_WIN -> dealerScore++;
                case PLAYER_WIN -> playerScore++;
                default -> {}
            }
            System.out.println("Счет " + playerScore + ":" + dealerScore);
            System.out.println("Для остановки введите 0, чтобы продолжить 1");
            int continueGame = in.nextInt();
            if (continueGame != 1) {
                return;
            }
        }
    }
}