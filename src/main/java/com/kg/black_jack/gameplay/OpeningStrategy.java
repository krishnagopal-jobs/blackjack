package com.kg.black_jack.gameplay;

import com.kg.black_jack.dealer.Dealer;
import com.kg.black_jack.player.Player;

import java.util.List;

public interface OpeningStrategy {

    List<Player> getPlayers();

    Dealer getDealer();

    BlackJackBufferCards getBufferCards();

    default void openThePlay() {
        try {
            for (var player : getPlayers()) {
                player.startPlay();
                player.addCard(getBufferCards().dealACard());
            }
            getDealer().startPlay();
            getDealer().addClosedCard(getBufferCards().dealACard());
            for (var player : getPlayers()) {
                player.addCard(getBufferCards().dealACard());
            }
            getDealer().addCard(getBufferCards().dealACard());
        } catch (BustedException e) {
            throw new RuntimeException(e);
        }
        for (var player : getPlayers()) {
            System.out.println(player);
        }

        System.out.println(getDealer());
    }
}
