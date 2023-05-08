package com.kg.black_jack.gameplay;

import mockit.integration.junit5.JMockitExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JMockitExtension.class)
class BustedExceptionTest {

    @Test
    void testBustedExceptionCreation() {
        List<Card> cards = Arrays.asList(
                new Card(Card.Rank.ACE, Card.Suit.HEARTS),
                new Card(Card.Rank.KING, Card.Suit.CLUBS)
        );

        BustedException bustedException = new BustedException(cards);

        assertNotNull(bustedException);
        assertEquals(2, bustedException.getCards().size());
        assertEquals(Card.Rank.ACE, bustedException.getCards().get(0).rank());
        assertEquals(Card.Suit.HEARTS, bustedException.getCards().get(0).suit());
        assertEquals(Card.Rank.KING, bustedException.getCards().get(1).rank());
        assertEquals(Card.Suit.CLUBS, bustedException.getCards().get(1).suit());
    }

    @Test
    void testImmutableCards() {
        List<Card> cards = Arrays.asList(
                new Card(Card.Rank.ACE, Card.Suit.HEARTS),
                new Card(Card.Rank.KING, Card.Suit.CLUBS)
        );

        BustedException bustedException = new BustedException(cards);

        cards.set(0, new Card(Card.Rank.TWO, Card.Suit.DIAMONDS));

        assertNotEquals(cards.get(0), bustedException.getCards().get(0));
        assertEquals(Card.Rank.ACE, bustedException.getCards().get(0).rank());
        assertEquals(Card.Suit.HEARTS, bustedException.getCards().get(0).suit());
    }
}
