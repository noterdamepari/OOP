package ru.nsu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class CardTest {

    @Test
    void cardShouldHaveCorrectSuitAndRank() {
        Card card = new Card(Suit.HEARTS, Rank.KING);

        assertEquals(Suit.HEARTS, card.getSuit());
        assertEquals(Rank.KING, card.getRank());
    }

    @Test
    void cardShouldHaveValueFromRank() {
        Card card = new Card(Suit.HEARTS, Rank.KING);

        assertEquals(Rank.KING.getValue(), card.getValue());
    }

    @Test
    void cardShouldNotBeHiddenByDefault() {
        Card card = new Card(Suit.HEARTS, Rank.KING);

        assertFalse(card.isHidden());
    }

    @Test
    void aceShouldChangeValueToOne() {
        Card card = new Card(Suit.HEARTS, Rank.ACE);

        assertEquals(Rank.ACE.getValue(), card.getValue());

        card.setAceToOne();

        assertEquals(1, card.getValue());
    }

    @Test
    void nonAceShouldNotChangeValue() {
        Card card = new Card(Suit.HEARTS, Rank.KING);

        int originalValue = card.getValue();

        card.setAceToOne();

        assertEquals(originalValue, card.getValue());
    }

    @Test
    void hiddenCardShouldHaveCorrectString() {
        Card card = new Card(Suit.HEARTS, Rank.KING);
        card.setHidden(true);

        assertEquals("<закрытая карта>", card.toString());
    }

    @Test
    void visibleCardShouldHaveCorrectString() {
        Card card = new Card(Suit.HEARTS, Rank.KING);

        String expected = String.format(
                "%s %s (%d)",
                Rank.KING.getName(),
                Suit.HEARTS.getName(),
                Rank.KING.getValue()
        );

        assertEquals(expected, card.toString());
    }

    @Test
    void setHiddenShouldChangeHiddenState() {
        Card card = new Card(Suit.HEARTS, Rank.KING);

        assertFalse(card.isHidden());

        card.setHidden(true);

        assertTrue(card.isHidden());

        card.setHidden(false);

        assertFalse(card.isHidden());
    }
}