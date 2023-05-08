package com.kg.black_jack.gameplay;

import com.kg.black_jack.Hand;
import com.kg.black_jack.dealer.Dealer;
import com.kg.black_jack.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game implements DealerStrategy, PlayerStrategy, OpeningStrategy {

    private final BlackJackBufferCards bufferCards;

    private final List<Player> players = new ArrayList<>();

    private final Dealer dealer;

    public Game(BlackJackBufferCards bufferCards, Dealer dealer) {
        this.bufferCards = bufferCards;
        this.dealer = dealer;
    }

    public void addPlayer(Player player) {
        player.waitToPlay();
        players.add(player);
    }


    public void addNonInitialCardsToDealer() throws BustedException {
        while (dealer.getTotalValue() <= 16) {
            dealer.addCard(bufferCards.dealACard());
        }
    }

    public void play() {
        openThePlay();
        for (var player : players) {
            dealWithAPlayer(player);
        }
        dealWithADealer();

        var dealerCount = dealer.getTotalValue();
        for (var player : players) {
            var playerTotalValue = player.getTotalValue();
            var playerLost = player.getState() == Hand.State.BUSTED || player.getState() == Hand.State.WITHDRAWN ||
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

    @Override
    public BlackJackBufferCards getBufferCards() {
        return this.bufferCards;
    }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    public Dealer getDealer() {
        return this.dealer;
    }
}
