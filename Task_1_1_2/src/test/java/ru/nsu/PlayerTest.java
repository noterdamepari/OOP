package ru.nsu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void newPlayerShouldHaveZeroScore() {
        Player player = new Player();

        assertEquals(0, player.getSum());
        assertFalse(player.isBlackJack());
    }

    @Test
    void addCardShouldIncreaseScore() {
        Player player = new Player();

        player.addCard(new Card(Suit.HEARTS, Rank.SEVEN));
        player.addCard(new Card(Suit.SPADES, Rank.FOUR));

        assertEquals(11, player.getSum());
    }

    @Test
    void addAceShouldGiveElevenPoints() {
        Player player = new Player();

        player.addCard(new Card(Suit.HEARTS, Rank.ACE));

        assertEquals(11, player.getSum());
    }

    @Test
    void aceShouldChangeFromElevenToOneWhenBust() {
        Player player = new Player();

        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(new Card(Suit.SPADES, Rank.KING));
        player.addCard(new Card(Suit.CLUBS, Rank.FIVE));

        assertEquals(16, player.getSum());
    }

    @Test
    void multipleAcesShouldBeConvertedWhenNecessary() {
        Player player = new Player();

        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(new Card(Suit.SPADES, Rank.ACE));
        player.addCard(new Card(Suit.CLUBS, Rank.KING));

        assertEquals(12, player.getSum());
    }

    @Test
    void aceShouldRemainElevenWhenItDoesNotCauseBust() {
        Player player = new Player();

        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(new Card(Suit.SPADES, Rank.FIVE));

        assertEquals(16, player.getSum());
    }

    @Test
    void twoCardsWithSumTwentyOneShouldBeBlackJack() {
        Player player = new Player();

        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(new Card(Suit.SPADES, Rank.KING));

        assertTrue(player.isBlackJack());
    }

    @Test
    void twentyOneWithMoreThanTwoCardsShouldNotBeBlackJack() {
        Player player = new Player();

        player.addCard(new Card(Suit.HEARTS, Rank.SEVEN));
        player.addCard(new Card(Suit.SPADES, Rank.SEVEN));
        player.addCard(new Card(Suit.CLUBS, Rank.SEVEN));

        assertEquals(21, player.getSum());
        assertFalse(player.isBlackJack());
    }

    @Test
    void twentyPointsShouldNotBeBlackJack() {
        Player player = new Player();

        player.addCard(new Card(Suit.HEARTS, Rank.TEN));
        player.addCard(new Card(Suit.SPADES, Rank.QUEEN));

        assertFalse(player.isBlackJack());
    }

    @Test
    void revealCardShouldReturnRevealedCard() {
        Player player = new Player();
        Card card = new Card(Suit.HEARTS, Rank.ACE);

        player.addCard(card);

        Card revealedCard = player.revealCard(0);

        assertSame(card, revealedCard);
    }

    @Test
    void printHandShouldPrintScore() {
        Player player = new Player();
        player.addCard(new Card(Suit.HEARTS, Rank.TEN));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(output));
        player.printHand();

        System.setOut(originalOut);

        assertTrue(output.toString().contains("-> 10"));
    }

    @Test
    void printHandWithHiddenSumShouldNotPrintScore() {
        Player player = new Player();
        player.addCard(new Card(Suit.HEARTS, Rank.TEN));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(output));
        player.printHand(true);

        System.setOut(originalOut);

        assertFalse(output.toString().contains("-> 10"));
    }
}
