package ru.nsu;

public class Card {
    public String suit;
    public Rank rank;
    public int value;

    Card(String suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
        this.value = rank.getValue();
    }

    public void setAceToOne(){
        if (rank == Rank.ACE){
            value = 1;
        }
    }


    public void print(){
        System.out.printf("%s %s %d\n", suit, rank, value);
    }
}
