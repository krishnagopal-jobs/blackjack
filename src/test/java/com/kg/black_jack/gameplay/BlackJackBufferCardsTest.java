package com.kg.black_jack.gameplay;

import mockit.integration.junit5.JMockitExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JMockitExtension.class)
class BlackJackBufferCardsTest {

    private BlackJackBufferCards blackJackBufferCards;

    @BeforeEach
    void setUp() {
        blackJackBufferCards = new BlackJackBufferCards();
    }

    @Test
    void testInitialDeckSize() {
        int initialSize = blackJackBufferCards.cards.size();
        assertEquals(208, initialSize);
    }

    @Test
    void testAddADeck() {
        blackJackBufferCards.addADeck();
        int newSize = blackJackBufferCards.cards.size();
        assertEquals(260, newSize);
    }

    @Test
    void testDealACard() {
        Card card = blackJackBufferCards.dealACard();
        assertNotNull(card);
        assertEquals(207, blackJackBufferCards.cards.size());
    }

    @Test
    void testDealAllCards() {
        Set<Card> dealtCards = new HashSet<>();

        for (int i = 0; i < 208; i++) {
            Card card = blackJackBufferCards.dealACard();
            assertNotNull(card);
            dealtCards.add(card);
        }

        assertEquals(0, blackJackBufferCards.cards.size());
        assertEquals(52, dealtCards.size());
    }

    @Test
    void testRefillDecks() {
        for (int i = 0; i < 208; i++) {
            blackJackBufferCards.dealACard();
        }
        assertEquals(0, blackJackBufferCards.cards.size());

        Card card = blackJackBufferCards.dealACard();
        assertNotNull(card);
        assertEquals(207, blackJackBufferCards.cards.size());
    }

    @Test
    void testToString() {
        String cardsString = blackJackBufferCards.toString();
        assertNotNull(cardsString);
        assertFalse(cardsString.isEmpty());
    }
}
