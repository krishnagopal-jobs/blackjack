package com.kg;

import com.kg.black_jack.dealer.Dealer;
import com.kg.black_jack.gameplay.BlackJackBufferCards;
import com.kg.black_jack.gameplay.Game;
import com.kg.black_jack.player.Player;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        BlackJackBufferCards bufferCards = new BlackJackBufferCards();
        Dealer dealer = new Dealer();
        Game game = new Game(bufferCards, dealer);
        game.addPlayer(new Player("Player1", scanner));
        game.addPlayer(new Player("Player2", scanner));
        game.addPlayer(new Player("Player3", scanner));
        game.play();

    }
}