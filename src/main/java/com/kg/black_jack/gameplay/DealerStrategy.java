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
            System.out.printf("Dealer %s is busted with count %d.%n", getDealer(), getDealer().getTotalValue());
        }
    }

    void addNonInitialCardsToDealer() throws BustedException;

    Dealer getDealer();
}
