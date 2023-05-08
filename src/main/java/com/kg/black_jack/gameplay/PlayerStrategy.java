package com.kg.black_jack.gameplay;

import com.kg.black_jack.player.Player;

import static java.util.Objects.requireNonNull;

public interface PlayerStrategy {

    BlackJackBufferCards getBufferCards();

    default void dealWithAPlayer(Player player) {
        requireNonNull(player);
        while (player.getTotalValue() < 21) {
            switch (player.makeADecision()) {
                case HIT -> {
                    try {
                        player.addCard(getBufferCards().dealACard());
                    } catch (BustedException e) {
                        System.out.printf("Player %s is busted with total %s.%n", player.getName(), player.getTotalValue());
                    }
                    System.out.println(player);
                }
                case STAND -> {
                    return;
                }
                case WITHDRAW -> {
                    player.withdrawFromPlay();
                    return;
                }
            }
        }
    }
}
