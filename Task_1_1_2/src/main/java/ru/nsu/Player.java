package ru.nsu;


import java.util.ArrayList;
import lombok.Getter;


/**
 * Player class.
 */
@Getter
public class Player {
    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Integer> aces = new ArrayList<>();
    private int cnt = 0;
    private int sum;
    private int aceCnt;

    /**
     * Добавляет карту в руку игрока.
     *
     * @param card карта, добавляемая в руку
     */
    public void addCard(Card card) {
        hand.add(card);
        cnt++;
        sum += card.getValue();
        if (card.getRank() == Rank.ACE) {
            aceCnt++;
            aces.add(cnt - 1);
        }

        while (sum > 21 && aceCnt > 0) {
            sum -= 10;
            int idx = aces.remove(0);
            Card ace = hand.remove(idx);
            ace.setAceToOne();
            hand.add(idx, ace);
            aceCnt--;
        }
    }

    /**
     * Проверяет, является ли текущая рука игрока блэкджеком.
     *
     * @return true, если игрок имеет блэкджек, иначе false
     */
    public boolean isBlackJack() {
        return sum == 21;
    }

    /**
     * Печатает руку игрока.
     *
     * @param hideSum отображение суммы руки
     */
    public void printHand(boolean hideSum) {
        if (hideSum) {
            System.out.println(hand.toString());
        } else {
            System.out.println(hand.toString() + " -> " + sum);
        }
    }

    /**
     * Печатает руку игрока.
     */
    public void printHand() {
        System.out.println(hand.toString() + " -> " + sum);
    }
}
