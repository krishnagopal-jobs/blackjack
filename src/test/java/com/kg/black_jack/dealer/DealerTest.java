package com.kg.black_jack.dealer;

import com.kg.black_jack.Hand;
import com.kg.black_jack.gameplay.BustedException;
import com.kg.black_jack.gameplay.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    void testAddClosedCard() {
        Card card = new Card(Card.Rank.TWO, Card.Suit.CLUBS);
        dealer.addClosedCard(card);
        assertEquals(card, dealer.closedCard);
    }

    @Test
    void testOpenTheClosedCard() {
        Card card = new Card(Card.Rank.TWO, Card.Suit.CLUBS);
        dealer.addClosedCard(card);

        try {
            dealer.openTheClosedCard();
            assertTrue(dealer.getCards().contains(card));
        } catch (BustedException e) {
            fail("Unexpected BustedException");
        }
    }

    @Test
    void testOpenTheClosedCardCausesBust() {
        Card card1 = new Card(Card.Rank.TEN, Card.Suit.CLUBS);
        Card card2 = new Card(Card.Rank.JACK, Card.Suit.HEARTS);
        Card card3 = new Card(Card.Rank.QUEEN, Card.Suit.SPADES);

        try {
            dealer.addCard(card1);
            dealer.addCard(card2);
            dealer.addClosedCard(card3);
            dealer.openTheClosedCard();
            fail("Expected BustedException");
        } catch (BustedException e) {
            assertEquals(Hand.State.BUSTED, dealer.getState());
        }
    }
}
