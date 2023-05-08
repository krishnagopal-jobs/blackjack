package com.kg.black_jack.gameplay;

import java.util.Collections;
import java.util.Stack;

public class BlackJackBufferCards {

    private final Stack<Card> cards = new Stack<>();

    public BlackJackBufferCards() {
        addADeck();
        addADeck();
        addADeck();
        addADeck();
    }

    public void addADeck() {
        for (var rank : Card.Rank.values()) {
            for (var suit : Card.Suit.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public Card dealACard() {
        return cards.pop();
    }

    public String toString() {
        return cards.toString();
    }

}
