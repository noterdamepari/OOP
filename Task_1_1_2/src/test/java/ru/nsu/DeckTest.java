package ru.nsu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @Test
    void oneDeckShouldContain52Cards() {
        Deck deck = new Deck(1);

        int count = 0;

        for (int i = 0; i < 52; i++) {
            deck.takeCard();
            count++;
        }

        assertEquals(52, count);

        assertThrows(IllegalStateException.class, () -> deck.takeCard());
    }
    @Test
    void oneDeckShouldContainFourOfEachRank() {
        Deck deck = new Deck(1);

        int[] rankCount = new int[Rank.values().length];

        for (int i = 0; i < 52; i++) {
            Card card = deck.takeCard();
            rankCount[card.getRank().ordinal()]++;
        }

        for (int count : rankCount) {
            assertEquals(4, count);
        }
    }
    @Test
    void oneDeckShouldContainThirteenCardsOfEachSuit() {
        Deck deck = new Deck(1);

        int[] suitCount = new int[Suit.values().length];

        for (int i = 0; i < 52; i++) {
            Card card = deck.takeCard();
            suitCount[card.getSuit().ordinal()]++;
        }

        for (int count : suitCount) {
            assertEquals(13, count);
        }
    }
    @Test
    void takenCardShouldBeVisibleByDefault() {
        Deck deck = new Deck(1);

        Card card = deck.takeCard();

        assertFalse(card.isHidden());
    }
    @Test
    void deckWithZeroCountShouldBeEmpty() {
        assertThrows(IllegalStateException.class, () -> new Deck(0));
    }
    @Test
    void takeCardWithTrueShouldHideCard() {
        Deck deck = new Deck(1);

        Card card = deck.takeCard(true);

        assertTrue(card.isHidden());
    }

    @Test
    void takeCardWithFalseShouldShowCard() {
        Deck deck = new Deck(1);

        Card card = deck.takeCard(false);

        assertFalse(card.isHidden());
    }

}