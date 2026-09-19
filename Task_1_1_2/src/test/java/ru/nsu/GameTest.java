package ru.nsu;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void playerBlackjackShouldWin() {
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, Rank.ACE),
                new Card(Suit.SPADES, Rank.KING),
                new Card(Suit.CLUBS, Rank.NINE),
                new Card(Suit.DIAMONDS, Rank.SEVEN)
        );

        Deck deck = new Deck(cards);
        Scanner scanner = new Scanner("0");

        Game game = new Game(scanner, deck);

        assertEquals(
                GameResult.PLAYER_WIN,
                game.playRound()
        );
    }
    @Test
    void dealerShouldWinWithBlackjack() {
        Deck deck = new Deck(List.of(
                new Card(Suit.HEARTS, Rank.NINE),   // player
                new Card(Suit.SPADES, Rank.SEVEN),  // player
                new Card(Suit.CLUBS, Rank.ACE),     // dealer
                new Card(Suit.DIAMONDS, Rank.KING)  // dealer
        ));

        Game game = new Game(new Scanner("0"), deck);

        assertEquals(GameResult.DEALER_WIN, game.playRound());
    }
    @Test
    void playerShouldLoseWhenBust() {
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, Rank.TEN),
                new Card(Suit.SPADES, Rank.NINE),
                new Card(Suit.CLUBS, Rank.TEN),
                new Card(Suit.DIAMONDS, Rank.SIX),
                new Card(Suit.HEARTS, Rank.FIVE)
        );

        Deck deck = new Deck(cards);

        // Игрок сначала берет карту
        Scanner scanner = new Scanner("1");

        Game game = new Game(scanner, deck);

        assertEquals(
                GameResult.DEALER_WIN,
                game.playRound()
        );
    }
    @Test
    void playerShouldWinWhenDealerBusts() {
        Deck deck = new Deck(List.of(
                new Card(Suit.HEARTS, Rank.TEN),     // player = 17
                new Card(Suit.SPADES, Rank.SEVEN),
                new Card(Suit.CLUBS, Rank.TEN),      // dealer = 16
                new Card(Suit.DIAMONDS, Rank.SIX),
                new Card(Suit.HEARTS, Rank.KING)     // dealer -> 26
        ));

        Game game = new Game(new Scanner("0"), deck);

        assertEquals(GameResult.PLAYER_WIN, game.playRound());
    }
    @Test
    void playerShouldWinWithHigherScore() {
        Deck deck = new Deck(List.of(
                new Card(Suit.HEARTS, Rank.TEN),     // player
                new Card(Suit.SPADES, Rank.NINE),    // player = 19
                new Card(Suit.CLUBS, Rank.TEN),      // dealer
                new Card(Suit.DIAMONDS, Rank.EIGHT)  // dealer = 18
        ));

        Game game = new Game(new Scanner("0"), deck);

        assertEquals(GameResult.PLAYER_WIN, game.playRound());
    }
    @Test
    void dealerShouldWinWithHigherScore() {
        Deck deck = new Deck(List.of(
                new Card(Suit.HEARTS, Rank.TEN),     // player = 18
                new Card(Suit.SPADES, Rank.EIGHT),
                new Card(Suit.CLUBS, Rank.TEN),      // dealer = 19
                new Card(Suit.DIAMONDS, Rank.NINE)
        ));

        Game game = new Game(new Scanner("0"), deck);

        assertEquals(GameResult.DEALER_WIN, game.playRound());
    }
    @Test
    void gameShouldEndInDrawWhenScoresAreEqual() {
        Deck deck = new Deck(List.of(
                new Card(Suit.HEARTS, Rank.TEN),     // player = 18
                new Card(Suit.SPADES, Rank.EIGHT),
                new Card(Suit.CLUBS, Rank.NINE),     // dealer = 18
                new Card(Suit.DIAMONDS, Rank.NINE)
        ));

        Game game = new Game(new Scanner("0"), deck);

        assertEquals(GameResult.DRAW, game.playRound());
    }
    @Test
    void bothPlayersShouldGetDrawWithBlackjack() {
        Deck deck = new Deck(List.of(
                new Card(Suit.HEARTS, Rank.ACE),    // player
                new Card(Suit.SPADES, Rank.KING),   // player
                new Card(Suit.CLUBS, Rank.ACE),     // dealer
                new Card(Suit.DIAMONDS, Rank.QUEEN) // dealer
        ));

        Game game = new Game(new Scanner("0"), deck);

        assertEquals(GameResult.DRAW, game.playRound());
    }
    @Test
    void aceShouldCountAsOneWhenNecessary() {
        Deck deck = new Deck(List.of(
                new Card(Suit.HEARTS, Rank.ACE),
                new Card(Suit.SPADES, Rank.NINE),
                new Card(Suit.CLUBS, Rank.TEN),
                new Card(Suit.DIAMONDS, Rank.SIX),
                new Card(Suit.HEARTS, Rank.FIVE)
        ));

        Game game = new Game(new Scanner("0"), deck);

        assertEquals(GameResult.DEALER_WIN, game.playRound());
    }

}