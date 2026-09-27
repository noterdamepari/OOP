package ru.nsu;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deck class.
 */
public class Deck {
    private ArrayList<Card> storage = new ArrayList<>();
    // private Card[] storage = new Card[52];

    /**
     * Создаёт колоду, содержащую указанное количество стандартных колод.
     *
     * @param cnt количество стандартных колод
     */
    public Deck(int cnt) {
        if (cnt <= 0) {
            throw new IllegalStateException("Количество колод не может быть нулевым");
        }
        Suit[] suits = {Suit.DIAMONDS, Suit.SPADES, Suit.CLUBS, Suit.HEARTS};
        Rank[] ranks = {Rank.TW0, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN,
            Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING, Rank.ACE};
        // deck filling
        for (int i = 0; i < cnt; i++) {
            for (Suit suit : suits) {
                for (Rank rank : ranks) {
                    storage.add(new Card(suit, rank));
                }
            }
        }
        Collections.shuffle(storage);
    }

    /**
     * Создаёт колоду, содержащую указанные карты.
     *
     * @param cards массив карт
     */
    public Deck(List<Card> cards) {
        storage.addAll(cards);
    }

    public void printDeck() {
        System.out.println(storage.toString());
    }

    /**
     * Извлекает верхнюю карту из колоды.
     *
     * @return извлечённая карта
     * @throws IllegalStateException если колода пуста
     */
    public Card takeCard() {
        if (storage.isEmpty()) {
            throw new IllegalStateException("Колода закончилась");
        }
        return storage.remove(0);
    }

    /**
     * Извлекает верхнюю карту и устанавливает её видимость.
     *
     * @param hidden true, если карта должна быть закрыта
     * @return извлечённая карта
     * @throws IllegalStateException если колода пуста
     */
    public Card takeCard(boolean hidden) {
        Card card = takeCard();
        card.setHidden(hidden);
        return card;
    }
}
