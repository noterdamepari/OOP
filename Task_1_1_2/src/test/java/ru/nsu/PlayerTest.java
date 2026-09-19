package ru.nsu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void newPlayerShouldHaveEmptyHandAndZeroScore() {
        Player player = new Player();

        assertTrue(player.getHand().isEmpty());
        assertTrue(player.getAces().isEmpty());
        assertEquals(0, player.getCnt());
        assertEquals(0, player.getSum());
        assertEquals(0, player.getAceCnt());
    }

    @Test
    void addCardShouldAddCardToHand() {
        Player player = new Player();
        Card card = new Card(Suit.HEARTS, Rank.TEN);

        player.addCard(card);

        assertEquals(1, player.getHand().size());
        assertSame(card, player.getHand().get(0));
        assertEquals(1, player.getCnt());
    }

    @Test
    void addCardShouldIncreaseSum() {
        Player player = new Player();

        player.addCard(new Card(Suit.HEARTS, Rank.SEVEN));
        player.addCard(new Card(Suit.SPADES, Rank.FOUR));

        assertEquals(11, player.getSum());
        assertEquals(2, player.getCnt());
    }

    @Test
    void addAceShouldIncreaseAceCount() {
        Player player = new Player();

        player.addCard(new Card(Suit.HEARTS, Rank.ACE));

        assertEquals(1, player.getAceCnt());
        assertEquals(1, player.getAces().size());
        assertEquals(11, player.getSum());
    }

    @Test
    void aceShouldChangeFromElevenToOneWhenPlayerBusts() {
        Player player = new Player();

        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(new Card(Suit.SPADES, Rank.KING));
        player.addCard(new Card(Suit.CLUBS, Rank.FIVE));

        assertEquals(16, player.getSum());
        assertEquals(0, player.getAceCnt());
        assertEquals(0, player.getAces().size());

        assertEquals(1, player.getHand().get(0).getValue());
    }

    @Test
    void multipleAcesShouldBeConvertedWhenNecessary() {
        Player player = new Player();

        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(new Card(Suit.SPADES, Rank.ACE));
        player.addCard(new Card(Suit.CLUBS, Rank.KING));

        assertEquals(12, player.getSum());
        assertEquals(0, player.getAceCnt());
        assertEquals(1, player.getHand().get(0).getValue());
        assertEquals(1, player.getHand().get(1).getValue());
    }

    @Test
    void aceShouldRemainElevenWhenItDoesNotCauseBust() {
        Player player = new Player();

        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(new Card(Suit.SPADES, Rank.FIVE));

        assertEquals(16, player.getSum());
        assertEquals(1, player.getAceCnt());
        assertEquals(11, player.getHand().get(0).getValue());
    }
}
