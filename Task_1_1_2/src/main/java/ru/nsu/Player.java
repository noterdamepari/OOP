package ru.nsu;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Player {
    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Integer> aces = new ArrayList<>();
    private int cnt = 0;
    private int sum;
    private int aceCnt;

    public void addCard(Card card){
        hand.add(card);
        cnt++;
        sum += card.getValue();
        if (card.getRank() == Rank.ACE){
            aceCnt++;
            aces.add(cnt-1);
        }

        while (sum > 21 && aceCnt > 0){
            sum -= 10;
            int idx = aces.remove(0);
            Card ace = hand.remove(idx);
            ace.setAceToOne();
            hand.add(idx, ace);
            aceCnt--;
        }
    }

    public boolean isBlackJack(){
        return sum == 21;
    }

    public void printHand(){
        System.out.println(hand.toString() + " -> " + sum);
    }
}
