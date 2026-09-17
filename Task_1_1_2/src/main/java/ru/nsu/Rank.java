package ru.nsu;

import lombok.Getter;

@Getter
public enum Rank {
    TW0(2, "Двойка"),
    THREE(3, "Тройка"),
    FOUR(4, "Четверка"),
    FIVE(5, "Пятерка"),
    SIX(6, "Шестерка"),
    SEVEN(7, "Семерка"),
    EIGHT(8, "Восьмерка"),
    NINE(9, "Девятка"),
    TEN(10, "Десятка"),
    JACK(10, "Валет"),
    QUEEN(10, "Дама"),
    KING(10, "Король"),
    ACE(11, "Туз");

    private final int value;
    private final String name;

    Rank(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
