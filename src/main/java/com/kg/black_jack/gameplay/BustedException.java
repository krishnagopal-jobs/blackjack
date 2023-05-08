package com.kg.black_jack.gameplay;

import java.util.ArrayList;
import java.util.List;

public class BustedException extends Exception {

    private final List<Card> cards;

    public BustedException(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> getCards() {
        return cards;
    }
}
