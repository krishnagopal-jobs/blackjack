package com.kg.black_jack.gameplay;

import com.kg.black_jack.Hand;
import com.kg.black_jack.dealer.Dealer;
import com.kg.black_jack.player.Player;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private final BlackJackBufferCards bufferCards;

    private final Set<Player> players = new HashSet<>();

    private final Dealer dealer;

    public Game(BlackJackBufferCards bufferCards, Dealer dealer) {
        this.bufferCards = bufferCards;
        this.dealer = dealer;
    }

    public void addPlayer(Player player) {
        player.waitToPlay();
        players.add(player);
    }

    public void initialDeal() {
        try {
            for (var player : players) {
                player.startPlay();
                player.addCard(bufferCards.dealACard());
            }
            dealer.startPlay();
            dealer.addClosedCard(bufferCards.dealACard());
            for (var player : players) {
                player.addCard(bufferCards.dealACard());
            }
            dealer.addCard(bufferCards.dealACard());
        } catch (BustedException e) {
            throw new RuntimeException(e);
        }
        for (var player : players) {
            System.out.println(player);
        }

        System.out.println(dealer);
    }

    public void addNonInitialCardsToDealer() throws BustedException {
        while (dealer.getTotalValue() <= 16) {
            dealer.addCard(bufferCards.dealACard());
        }
    }

    public void play() {
        initialDeal();
        for (var player : players) {
            player_making_decision:
            while (player.getTotalValue() < 21) {
                switch (player.makeADecision()) {
                    case HIT -> {
                        try {
                            player.addCard(bufferCards.dealACard());
                        } catch (BustedException e) {
                            System.out.printf("Player %s is busted with total %s.%n", player.getName(), player.getTotalValue());
                        }
                        System.out.println(player);
                    }
                    case STAND -> {
                        break player_making_decision;
                    }
                    case WITHDRAW -> {
                        player.withdrawFromPlay();
                        break player_making_decision;
                    }
                }
            }
        }
        try {
            dealer.openTheClosedCard();
            System.out.println(dealer);
            addNonInitialCardsToDealer();
            System.out.println(dealer);
        } catch (BustedException e) {
            System.out.println("Dealer is busted.");
        }

        var dealerCount = dealer.getTotalValue();
        for (var player : players) {
            var playerTotalValue = player.getTotalValue();
            var playerLost = player.getState() == Hand.State.BUSTED ||
                    (dealer.getState() != Hand.State.BUSTED && playerTotalValue < dealerCount);
            if (!playerLost && playerTotalValue > dealerCount) {
                System.out.printf("Player %s with count: %s won.%n", player.getName(), player.getTotalValue());
            } else if (!playerLost && playerTotalValue == dealerCount) {
                System.out.printf("Player %s with count: %s tied.%n", player.getName(), player.getTotalValue());
            } else {
                System.out.printf("Player %s with count: %s lost.%n", player.getName(), player.getTotalValue());
            }
        }

    }

}
