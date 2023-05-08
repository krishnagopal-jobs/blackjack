package com.kg.black_jack.gameplay;

import static java.util.Objects.isNull;

public record Card(Rank rank, Suit suit) implements Comparable<Card> {
    public Card {
        if (isNull(suit)) {
            throw new IllegalArgumentException("Suit cannot be null");
        }
        if (isNull(rank)) {
            throw new IllegalArgumentException("Rank cannot be null");
        }
    }

    @Override
    public int compareTo(Card other) {
        int rankComparison = rank.compareTo(other.rank);
        if (rankComparison != 0) {
            return rankComparison;
        }
        return suit.compareTo(other.suit);
    }

    public enum Suit {HEARTS, DIAMONDS, CLUBS, SPADES}

    public enum Rank {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}
}
