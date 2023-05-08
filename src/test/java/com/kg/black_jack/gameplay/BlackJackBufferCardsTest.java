package com.kg.black_jack.gameplay;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackBufferCardsTest {
    private BlackJackBufferCards blackJackBufferCards;

    @BeforeEach
    void setUp() {
        blackJackBufferCards = new BlackJackBufferCards();
    }

    @Test
    void testInitialBufferSize() {
        // There are 52 cards in a deck, and we add 4 decks in the constructor
        assertEquals(52 * 4, blackJackBufferCards.cards.size());
    }

    @Test
    void testAddADeck() {
        blackJackBufferCards.addADeck();
        // There are now 5 decks in the buffer
        assertEquals(52 * 5, blackJackBufferCards.cards.size());
    }

    @Test
    void testDealACard() {
        Card dealtCard = blackJackBufferCards.dealACard();
        assertNotNull(dealtCard);
        // There are now 52 * 4 - 1 cards left in the buffer
        assertEquals(52 * 4 - 1, blackJackBufferCards.cards.size());
    }

    @Test
    void testUniqueCards() {
        Set<Card> uniqueCards = new HashSet<>();
        int initialBufferSize = blackJackBufferCards.cards.size();
        for (int i = 0; i < initialBufferSize; i++) {
            uniqueCards.add(blackJackBufferCards.dealACard());
        }
        // Expect 52 unique cards (each card in a deck is unique)
        assertEquals(52, uniqueCards.size());
    }
}
