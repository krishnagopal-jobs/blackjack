package com.kg.black_jack.gameplay;

import com.kg.black_jack.dealer.Dealer;

public interface DealerStrategy {

    default void dealWithADealer() {
        try {
            getDealer().openTheClosedCard();
            System.out.println(getDealer());
            addNonInitialCardsToDealer();
            System.out.println(getDealer());
        } catch (BustedException e) {
            System.out.println("Dealer is busted.");
        }
    }

    void addNonInitialCardsToDealer() throws BustedException;

    Dealer getDealer();
}
