package ru.nsu;

import lombok.Getter;

@Getter
public enum Suit {
    HEARTS("Черви"),
    DIAMONDS("Буби"),
    CLUBS("Крести"),
    SPADES("Пики");

    private final String name;

    Suit(String name) {
        this.name = name;
    }
}
