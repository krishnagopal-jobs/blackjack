package com.kg.black_jack.player;

import com.kg.black_jack.Hand;

import java.util.Scanner;

public class Player extends Hand {

    private final String name;

    private final Scanner scanner;

    public Player(String name, Scanner scanner) {
        this.name = name;
        this.scanner = scanner;
    }

    public String getName() {
        return name;
    }

    public void waitToPlay() {
        this.state = State.WAITING;
    }

    public void withdrawFromPlay() {
        this.state = State.WITHDRAWN;
    }

    public Action makeADecision() {
        System.out.print(getName() + ", Do you want to be hit(H) or to stand(S) or to withdraw(W): ");
        while (true) {
            switch (scanner.next().toUpperCase().trim()) {
                case "H" -> {
                    return Action.HIT;
                }
                case "S" -> {
                    return Action.STAND;
                }
                case "W" -> {
                    return Action.WITHDRAW;
                }
                default -> System.out.println("You must enter H or S or W");
            }
        }
    }

    public String toString() {
        return this.getName() + "\t" + this.state + "\t" + super.toString() + "\t";
    }

    public enum Action {
        HIT, WITHDRAW, STAND
    }
}
