package com.kg.black_jack.gameplay;

import java.util.Set;
import java.util.TreeSet;

public class BustedException extends Exception {

    private final Set<Card> cards;

    public BustedException(Set<Card> cards) {
        this.cards = new TreeSet<>(cards);
    }

    public Set<Card> getCards() {
        return cards;
    }
}
