package ru.nsu;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> storage = new ArrayList<>();
    // private Card[] storage = new Card[52];

    public Deck(int cnt){
        int idx = 0;
        Suit[] suits = {Suit.DIAMONDS, Suit.SPADES, Suit.CLUBS, Suit.HEARTS};
        Rank[] ranks = {Rank.TW0, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT,
        Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING, Rank.ACE};
        // deck filling
        for (int i = 0; i < cnt; i++){
            for (Suit suit : suits){
                for (Rank rank : ranks){
                    storage.add(new Card(suit, rank));
                }
            }
        }
        Collections.shuffle(storage);
    }

    public void PrintDeck(){
        System.out.println(storage.toString());
    }

    public Card TakeCard(){
        return storage.remove(0);
    }

    public Card TakeCard(boolean hidden){
        Card card = storage.remove(0);
        card.setHidden(hidden);
        return card;
    }
}
