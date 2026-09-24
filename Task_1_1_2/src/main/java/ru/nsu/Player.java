package ru.nsu;


import java.util.ArrayList;
import lombok.Getter;


/**
 * Player class.
 */
public class Player {
    private ArrayList<Card> hand = new ArrayList<>();
    private int cnt = 0;

    public void addCard(Card card){
        hand.add(card);
        cnt++;
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

    public int getSum(){
        HandState state = getHandState();
        return state.sum;
    }

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

    public void printHand(boolean hideSum) {
        System.out.println(handToString(hideSum));
    }

    public void printHand() {
        System.out.println(handToString(false));
    }


    public Card revealCard(int index) {
        Card card = hand.get(index);
        card.setHidden(false);
        return card;
    }

}

//public class Player {
//    private ArrayList<Card> hand = new ArrayList<>();
//    private ArrayList<Integer> aces = new ArrayList<>();
//    private int cnt = 0;
//    @Getter
//    private int sum; // TODO: Переписать на метод getSum
//    private int aceCnt;
//
//    /**
//     * Добавляет карту в руку игрока.
//     *
//     * @param card карта, добавляемая в руку
//     */
//    public void addCard(Card card) {
//        hand.add(card);
//        cnt++;
//        sum += card.getValue();
//        if (card.getRank() == Rank.ACE) {
//            aceCnt++;
//            aces.add(cnt - 1);
//        }
//
//        while (sum > 21 && aceCnt > 0) {
//            sum -= 10;
//            int idx = aces.remove(0);
//            Card ace = hand.remove(idx);
//            ace.setAceToOne();
//            hand.add(idx, ace);
//            aceCnt--;
//        }
//    }
//
//    /**
//     * Проверяет, является ли текущая рука игрока блэкджеком.
//     *
//     * @return true, если игрок имеет блэкджек, иначе false
//     */
//    public boolean isBlackJack() {
//        return (sum == 21) && (hand.size() == 2);
//    }
//
//    /**
//     * Печатает руку игрока.
//     *
//     * @param hideSum отображение суммы руки
//     */
//    public void printHand(boolean hideSum) {
//        if (hideSum) {
////            System.out.println(getHandWithValues());
//        } else {
////            System.out.println(getHandWithValues());
////            System.out.println(hand.toString() + " -> " + sum);
//        }
//    }
//
//    /**
//     * Печатает руку игрока.
//     */
//    public void printHand() {
//        System.out.println(hand.toString() + " -> " + sum);
//    }
//
//    /**
//     * Открывает скрытую карту.
//     *
//     * @param index индекс карты
//     * @return открытая карта
//     */
//    public Card revealCard(int index) {
//        Card card = hand.get(index);
//        card.setHidden(false);
//        return card;
//    }
//}
