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
        int numberOfPlayers = 0;
        final Scanner scanner = new Scanner(System.in);
        do{
            String input = null;
            try {
                System.out.print("How many players do play? :");
                input = scanner.nextLine();
                numberOfPlayers = Integer.parseInt(input);
                System.out.println();
            } catch (Exception e) {
                System.out.println("Enter a positive integer. You entered "+ input);
            }
        } while (numberOfPlayers <= 0);

        BlackJackBufferCards bufferCards = new BlackJackBufferCards(numberOfPlayers+1);
        Dealer dealer = new Dealer();
        Game game = new Game(bufferCards, dealer);
        for(int i=0; i < numberOfPlayers; i++) {
            System.out.printf("Enter the name of the player#%d ", (i+1));
            String playerName = scanner.nextLine();
            game.addPlayer(new Player(playerName, scanner));
        }
        game.play();

    }
}