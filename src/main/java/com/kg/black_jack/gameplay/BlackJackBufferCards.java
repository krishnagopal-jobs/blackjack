package com.kg.black_jack.gameplay;

import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

public class BlackJackBufferCards {

    final Stack<Card> cards = new Stack<>();

    private final int numberOfDecks;

    public BlackJackBufferCards() {
        this(4);
    }

    public BlackJackBufferCards(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
        for (int i =0; i < this.numberOfDecks ; i++) {
            addADeck();
        }
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
        try {
            return cards.pop();
        } catch (EmptyStackException e) {
            for (int i =0; i < this.numberOfDecks ; i++) {
                addADeck();
            }
            return cards.pop();
        }
    }

    public String toString() {
        return cards.toString();
    }

}
