package com.kg.black_jack;

import com.kg.black_jack.gameplay.BustedException;
import com.kg.black_jack.gameplay.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Hand {

    private final List<Card> cards = new ArrayList<>();
    protected State state;

    public State getState() {
        return state;
    }

    protected List<Card> getCards() {
        return cards;
    }

    public void resetCards() {
        cards.clear();
    }

    public void startPlay() {
        resetCards();
        this.state = State.IN_PLAY;
    }

    public void addCard(Card card) throws BustedException {
        getCards().add(card);
        if (getTotalValue() > 21) {
            this.state = State.BUSTED;
            throw new BustedException(getCards());
        }
    }

    public int getTotalValue() {
        final AtomicInteger numberOfAces = new AtomicInteger();
        int totalValue = getCards().stream().map((card) -> switch (card.rank()) {
            case ACE -> {
                numberOfAces.incrementAndGet();
                yield 1;
            }
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            case TEN, JACK, QUEEN, KING -> 10;
        }).mapToInt(Integer::intValue).sum();

        while ((totalValue + 10) <= 21 && numberOfAces.get() > 0) {
            totalValue += 10;
            numberOfAces.decrementAndGet();
        }
        return totalValue;
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public enum State {
        WAITING, IN_PLAY, BUSTED, WITHDRAWN
    }
}
