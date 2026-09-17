package ru.nsu;

import java.util.Scanner;

public class Game {
    private Scanner in;
    private Player player;
    private Player dealer;
    private Deck deck;
    private int deck_cnt;

    public Game(Scanner in, int deck_cnt){
        this.in = in;
        this.deck_cnt = deck_cnt;
    }

    private void printHands(){
        System.out.print("\tВаши карты: ");
        player.printHand();
        System.out.print("\tКарты дилера: ");
        dealer.printHand();
    }

    public GameResult playRound(){
        deck = new Deck(deck_cnt);
        player = new Player();
        dealer = new Player();

        // init
        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(deck.TakeCard());

        dealer.addCard(deck.TakeCard());
        dealer.addCard(deck.TakeCard(true));

        System.out.println("Дилер раздал карты");
        printHands();

        if (player.isBlackJack() && dealer.isBlackJack()){
            System.out.println("Ничья");
            return GameResult.DRAW;
        } else if (dealer.isBlackJack()){
            System.out.println("Дилер получил BlackJack, вы проиграли");
            return GameResult.DEALER_WIN;
        } else if (player.isBlackJack()){
            System.out.println("BlackJack! Вы выиграли");
            return GameResult.PLAYER_WIN;
        }
        //

        // player move
        System.out.print("Ваш ход\n-------\n");
        System.out.print("Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...\n");

        int something = in.nextInt();
        while (something == 1){
            Card card = deck.TakeCard();
            System.out.println("Вы открыли карту: " + card);
            player.addCard(card);
            printHands();

            if (player.getSum() > 21){
                System.out.println("Перебор, вы проиграли!");
                return GameResult.DEALER_WIN;
            }
        }

        // dealer move
        System.out.print("Ход дилера\n-------\n");
        Card hiddenCard = dealer.getHand().get(1);
        hiddenCard.setHidden(false);
        dealer.getHand().set(1, hiddenCard);
        System.out.println("Дилер открыл карту " + hiddenCard);
        printHands();

        while (dealer.getSum() < 17){
            Card card = deck.TakeCard();
            System.out.println("Дилер открыл карту " + card);
            dealer.addCard(card);
            printHands();

            if (dealer.getSum() > 21){
                System.out.println("Перебор, дилер проиграл!");
                return GameResult.PLAYER_WIN;
            }
        }

        if (player.getSum() > dealer.getSum()){
            System.out.println("Ваша сумма очков больше чем у дилера, вы выиграли!");
            return GameResult.PLAYER_WIN;
        } else if (player.getSum() < dealer.getSum()){
            System.out.println("Cумма очков дилера больше, вы проиграли!");
            return GameResult.DEALER_WIN;
        } else {
            return GameResult.DRAW;
        }
    }
}
