package com.kg.black_jack.gameplay;

import com.kg.black_jack.Hand;
import com.kg.black_jack.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStrategyTest {

    private PlayerStrategy playerStrategy;

    @BeforeEach
    void setUp() {
        playerStrategy = BlackJackBufferCards::new;
    }

    @Test
    void testDealWithAPlayerHit() {
        InputStream in = new ByteArrayInputStream(("""
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                H
                """).getBytes());
        var player = new Player("TestPlayer", new Scanner(in));
        player.startPlay();
        playerStrategy.dealWithAPlayer(player);
        assertTrue (player.getTotalValue() > 21, () -> player.getTotalValue() +"is the total count.");
        assertEquals(Hand.State.BUSTED, player.getState());
    }

    @Test
    void testDealWithAPlayerStand() {
        InputStream in = new ByteArrayInputStream("S\n".getBytes());
        var player = new Player("TestPlayer", new Scanner(in));
        assertThrows(AssertionError.class, () -> playerStrategy.dealWithAPlayer(player));
        player.startPlay();
        playerStrategy.dealWithAPlayer(player);
        assertEquals(0, player.getTotalValue());
        assertEquals(Hand.State.IN_PLAY, player.getState());
    }

    @Test
    void testDealWithAPlayerWithdraw() {
        InputStream in = new ByteArrayInputStream("W\n".getBytes());
        var player = new Player("TestPlayer", new Scanner(in));
        player.startPlay();
        playerStrategy.dealWithAPlayer(player);
        assertEquals(0, player.getTotalValue());
        assertEquals(Hand.State.WITHDRAWN, player.getState());
    }
}
