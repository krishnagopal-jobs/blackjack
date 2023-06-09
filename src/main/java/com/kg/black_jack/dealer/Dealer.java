package com.kg.black_jack.dealer;

import com.kg.black_jack.Hand;
import com.kg.black_jack.gameplay.BustedException;
import com.kg.black_jack.gameplay.Card;

import java.util.List;

public class Dealer extends Hand {

    Card closedCard;

    public Dealer() {
    }

    protected List<Card> getCards() {
        return super.getCards();
    }

    public void addClosedCard(Card card) {
        this.closedCard = card;
    }

    public void openTheClosedCard() throws BustedException {
        try {
            addCard(closedCard);
            closedCard = null;
        } catch (BustedException e) {
            this.state = State.BUSTED;
            throw e;
        }
    }

    public String toString() {
        return "Dealer " + "\t" + this.state + "\t" + super.toString() + "\t";
    }

}
