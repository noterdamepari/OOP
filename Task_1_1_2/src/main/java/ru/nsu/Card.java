package ru.nsu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
    private Suit suit;
    private Rank rank;
    private int value;
    private boolean hidden = false;

    Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
        this.value = rank.getValue();
    }

    public void setAceToOne(){
        if (rank == Rank.ACE){
            value = 1;
        }
    }

    @Override
    public String toString() {
        String res;
        if (!hidden){
            res = String.format("%s %s (%d)", rank.getName(), suit.getName(), value);
        } else {
             res = "<закрытая карта>";
        }
        return res;
    }

}
