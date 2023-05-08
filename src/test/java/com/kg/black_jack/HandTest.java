package com.kg.black_jack;

import com.kg.black_jack.gameplay.BustedException;
import com.kg.black_jack.gameplay.Card;
import mockit.integration.junit5.JMockitExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JMockitExtension.class)
class HandTest {
    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand() {
        };
        hand.state = Hand.State.WAITING;
    }

    @Test
    void testInitialState() {
        assertEquals(Hand.State.WAITING, hand.getState());
        assertTrue(hand.getCards().isEmpty());
    }

    @Test
    void testStartPlay() {
        hand.startPlay();
        assertEquals(Hand.State.IN_PLAY, hand.getState());
        assertTrue(hand.getCards().isEmpty());
    }

    @Test
    void testAddCard() throws BustedException {
        hand.startPlay();
        hand.addCard(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
        Set<Card> cards = Set.of(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
        assertEquals(cards, hand.getCards().stream().collect(Collectors.toSet()));
    }

    @Test
    void testAddCardBusted() {
        hand.startPlay();
        assertEquals(hand.state, Hand.State.IN_PLAY);
        assertThrows(BustedException.class, () -> {
            for (int i = 0; i < 3; i++) {
                hand.addCard(new Card(Card.Rank.EIGHT, Card.Suit.CLUBS));
            }
        });
        assertEquals(Hand.State.BUSTED, hand.getState());
    }

    @Test
    void testResetCards() {
        hand.startPlay();
        hand.resetCards();
        assertTrue(hand.getCards().isEmpty());
    }

    @Test
    void testGetTotalValue() throws BustedException {
        hand.startPlay();
        hand.addCard(new Card(Card.Rank.TWO, Card.Suit.CLUBS));
        hand.addCard(new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS));
        assertEquals(7, hand.getTotalValue());
    }

    @Test
    void testGetTotalValueWithAces() throws BustedException {
        hand.startPlay();
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        hand.addCard(new Card(Card.Rank.ACE, Card.Suit.SPADES));
        assertEquals(12, hand.getTotalValue());
    }
}
