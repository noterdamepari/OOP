package ru.nsu;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> storage = new ArrayList<>();
    // private Card[] storage = new Card[52];

    Deck(){
        int idx = 0;
        String[] suits = {"diamond", "club", "spade", "heart"};
        Rank[] ranks = {Rank.TW0, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT,
        Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING, Rank.ACE};
        // deck filling
        for (String suit : suits){
            for (Rank rank : ranks){
                storage.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(storage);
    }

    public void PrintDeck(){
        for (int i = 0; i < 52; i++){
            storage.get(i).print();
        }
    }

    public Card TakeCard(){
        return storage.remove(0);
    }
}
