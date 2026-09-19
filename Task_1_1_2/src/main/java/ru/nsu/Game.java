package ru.nsu;

import java.util.Scanner;


/**
 * Game class.
 */
public class Game {
    private final Scanner in;
    private Player player;
    private Player dealer;
    private final Deck deck;

    /**
     * Game constructor.
     */
    public Game(Scanner in, Deck deck) {
        this.in = in;
        this.deck = deck;
    }

    private void printHands(boolean hideDealerSum) {
        System.out.print("\tВаши карты: ");
        player.printHand();
        System.out.print("\tКарты дилера: ");
        dealer.printHand(hideDealerSum);
    }

    private GameResult checkBlackJack() {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            System.out.println("Ничья");
            return GameResult.DRAW;
        } else if (dealer.isBlackJack()) {
            System.out.println("Дилер получил BlackJack, вы проиграли");
            return GameResult.DEALER_WIN;
        } else if (player.isBlackJack()) {
            System.out.println("BlackJack! Вы выиграли");
            return GameResult.PLAYER_WIN;
        }
        return null;
    }


    /**
     * Запускает один раунд игры в блэкджек.
     *
     * @return результат раунда: победа игрока, победа дилера
     *         или ничья
     */
    public GameResult playRound() {
        player = new Player();
        dealer = new Player();

        // init
        player.addCard(deck.takeCard());
        player.addCard(deck.takeCard());

        dealer.addCard(deck.takeCard());
        dealer.addCard(deck.takeCard(true));

        System.out.println("Дилер раздал карты");
        printHands(true);

        GameResult res = checkBlackJack();

        if (res != null) {
            return res;
        }
        //

        // player move
        System.out.print("Ваш ход\n-------\n");
        System.out.print("Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...\n");

        int action = in.nextInt();
        while (action == 1) {
            Card card = deck.takeCard();
            System.out.println("Вы открыли карту: " + card);
            player.addCard(card);
            printHands(true);

            if (player.getSum() > 21) {
                System.out.println("Перебор, вы проиграли!");
                return GameResult.DEALER_WIN;
            }
            System.out.print("Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...\n");
            action = in.nextInt();
        }

        // dealer move
        System.out.print("Ход дилера\n-------\n");
        Card hiddenCard = dealer.getHand().get(1);
        hiddenCard.setHidden(false);
        dealer.getHand().set(1, hiddenCard);
        System.out.println("Дилер открыл карту " + hiddenCard);
        printHands(false);

        while (dealer.getSum() < 17) {
            Card card = deck.takeCard();
            System.out.println("Дилер открыл карту " + card);
            dealer.addCard(card);
            printHands(false);

            if (dealer.getSum() > 21) {
                System.out.println("Перебор, дилер проиграл!");
                return GameResult.PLAYER_WIN;
            }
        }

        if (player.getSum() > dealer.getSum()) {
            System.out.println("Ваша сумма очков больше чем у дилера, вы выиграли!");
            return GameResult.PLAYER_WIN;
        } else if (player.getSum() < dealer.getSum()) {
            System.out.println("Cумма очков дилера больше, вы проиграли!");
            return GameResult.DEALER_WIN;
        } else {
            return GameResult.DRAW;
        }
    }
}
