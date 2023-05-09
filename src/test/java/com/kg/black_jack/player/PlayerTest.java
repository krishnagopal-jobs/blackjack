package com.kg.black_jack.player;

import com.kg.black_jack.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer", new Scanner(System.in));
    }

    @Test
    void testPlayerName() {
        assertEquals("TestPlayer", player.getName());
    }

    @Test
    void testWaitToPlay() {
        player.waitToPlay();
        assertEquals(Hand.State.WAITING, player.getState());
    }

    @Test
    void testWithdrawFromPlay() {
        player.withdrawFromPlay();
        assertEquals(Hand.State.WITHDRAWN, player.getState());
    }

    @Test
    void testMakeADecision() {
        String input = "H\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        player = new Player("TestPlayer", new Scanner(System.in));
        assertEquals(Player.Action.HIT, player.makeADecision());

        input = "S\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        player = new Player("TestPlayer", new Scanner(System.in));
        assertEquals(Player.Action.STAND, player.makeADecision());

        input = "W\n";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        player = new Player("TestPlayer", new Scanner(System.in));
        assertEquals(Player.Action.WITHDRAW, player.makeADecision());
    }
}
