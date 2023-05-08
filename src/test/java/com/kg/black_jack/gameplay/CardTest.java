package com.kg.black_jack.gameplay;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void testInvalidSuit() {
        assertThrows(IllegalArgumentException.class, () -> new Card(Card.Rank.ACE, null));
    }

    @Test
    void testInvalidRank() {
        assertThrows(IllegalArgumentException.class, () -> new Card(null, Card.Suit.HEARTS));
    }

    @Test
    void testValidCard() {
        assertDoesNotThrow(() -> new Card(Card.Rank.ACE, Card.Suit.HEARTS));
    }

    @Test
    void testEqualsAndHashCode() {
        Card card1 = new Card(Card.Rank.ACE, Card.Suit.HEARTS);
        Card card2 = new Card(Card.Rank.ACE, Card.Suit.HEARTS);
        assertEquals(card1, card2);
        assertEquals(card1.hashCode(), card2.hashCode());
    }

    @ParameterizedTest
    @CsvSource({
            "ACE, HEARTS, ACE, HEARTS, 0",
            "TWO, HEARTS, TWO, HEARTS, 0",
            "TEN, CLUBS, TEN, CLUBS, 0",
            "ACE, SPADES, ACE, DIAMONDS, 2",
            "ACE, HEARTS, TWO, HEARTS, -1",
            "THREE, SPADES, TWO, SPADES, 1",
            "ACE, CLUBS, ACE, DIAMONDS, 1",
            "JACK, SPADES, JACK, CLUBS, 1"
    })
    void testCompareTo(Card.Rank rank1, Card.Suit suit1, Card.Rank rank2, Card.Suit suit2, int expectedResult) {
        Card card1 = new Card(rank1, suit1);
        Card card2 = new Card(rank2, suit2);
        assertEquals(expectedResult, card1.compareTo(card2));
    }
}
