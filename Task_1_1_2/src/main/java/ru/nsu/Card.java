package ru.nsu;

import lombok.Getter;
import lombok.Setter;

/**
 * Card class.
 */
@Getter
@Setter
public class Card {
    private Suit suit;
    private Rank rank;
    private int value;
    private boolean hidden = false;

    /**
     * Создаёт новую игральную карту.
     *
     * @param suit масть карты
     * @param rank достоинство карты
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = rank.getValue();
    }

    /**
     * Изменяет значение туза с 11 на 1.
     * Метод применяется только к картам достоинства ACE.
     */
    public void setAceToOne() {
        if (rank == Rank.ACE) {
            value = 1;
        }
    }

    /**
     * Возвращает строковое представление карты.
     *
     * @return название карты, масть и её значение,
     *         либо сообщение о закрытой карте
     */
    @Override
    public String toString() {
        String res;
        if (!hidden) {
            res = String.format("%s %s (%d)", rank.getName(), suit.getName(), value);
        } else {
            res = "<закрытая карта>";
        }
        return res;
    }

}
