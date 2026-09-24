package ru.nsu;

import java.util.ArrayList;


/**
 * Player class.
 */
public class Player {
    private ArrayList<Card> hand = new ArrayList<>();

    /**
     * Добавляет карту в руку игрока.
     *
     * @param card карта, добавляемая в руку
     */
    public void addCard(Card card){
        hand.add(card);
    }

    private HandState getHandState() {
        int sum = 0;
        int aceCnt = 0;

        for (Card card : hand) {
            sum += card.getValue();
            if (card.getRank() == Rank.ACE) {
                aceCnt++;
            }
        }

        while (sum > 21 && aceCnt > 0) {
            sum -= 10;
            aceCnt--;
        }

        return new HandState(sum,aceCnt);
    }


    /**
     * Получение суммы очков игрока.
     *
     * @return кол-во очков
     */
    public int getSum(){
        HandState state = getHandState();
        return state.sum;
    }


    /**
     * Проверяет, является ли текущая рука игрока блэкджеком.
     *
     * @return true, если игрок имеет блэкджек, иначе false
     */
    public boolean isBlackJack() {
        return (getSum() == 21) && (hand.size() == 2);
    }

    private String handToString(boolean hideSum){
        HandState state = getHandState();
        int aceCnt = state.aceCnt;
        StringBuilder sb = new StringBuilder();

        sb.append('[');

        for (Card card : hand){
            if (sb.length() > 1){
                sb.append(',');
            }

            if (card.isHidden()) {
                sb.append("<закрытая карта>");
                continue;
            }
            Suit suit = card.getSuit();
            Rank rank = card.getRank();
            int value = card.getValue();
            if (rank == Rank.ACE){
                if (aceCnt > 0){
                    sb.append(String.format("%s %s (%d)", suit.getName(), rank.getName(), card.getValue()));
                    aceCnt--;
                } else {
                    sb.append(String.format("%s %s (1)", suit.getName(), rank.getName()));
                }
            } else {
                sb.append(String.format("%s %s (%d)", suit.getName(), rank.getName(), card.getValue()));
            }
        }
        sb.append(']');

        if (!hideSum) {
            sb.append(String.format(" -> %d", state.sum));
        }

        return sb.toString();
    }


    /**
     * Печатает руку игрока.
     *
     * @param hideSum отображение суммы руки
     */
    public void printHand(boolean hideSum) {
        System.out.println(handToString(hideSum));
    }

    /**
     * Печатает руку игрока.
     */
    public void printHand() {
        System.out.println(handToString(false));
    }

    /**
     * Открывает скрытую карту.
     *
     * @param index индекс карты
     * @return открытая карта
     */
    public Card revealCard(int index) {
        Card card = hand.get(index);
        card.setHidden(false);
        return card;
    }
}