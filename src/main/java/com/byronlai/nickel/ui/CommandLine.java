package com.byronlai.nickel.ui;

import com.byronlai.nickel.logic.Game;
import com.byronlai.nickel.logic.GameResult;
import com.byronlai.nickel.logic.Outcome;
import com.byronlai.nickel.logic.Shape;
import java.util.Scanner;

/* A command line interface for playing rock paper scissors lizard spock. */
public class CommandLine {
    private Game game;
    private Scanner scanner;

    /*
     * Run a REPL loop. The player inputs his move and the result will be shown.
     * Then the whole process repeats.
     */
    public void run() {
        game = new Game();
        scanner = new Scanner(System.in);

        while (true) {
            Shape userChoice = getUserChoice();

            if (userChoice == null)
                break;

            GameResult result = game.play(userChoice);
            printResult(result);
            printStats();
        }
    }

    /*
     * Read the move the player chose. Retry until the user enters a valid
     * choice.
     */
    private Shape getUserChoice() {
        while (true) {
            System.out.print("You chose (Rock, Paper, Scissors, Lizard or Spock): ");

            if (!scanner.hasNextLine())
                return null;

            String input = scanner.nextLine();

            try {
                return Shape.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {}
        }
    }

    /* Print the move the bot made and the outcome of the game. */
    private void printResult(GameResult result) {
        Shape computerChoice = result.getComputerChoice();
        Outcome outcome = result.getOutcome();

        System.out.println("Computer chose " + computerChoice.toString().toLowerCase());
        System.out.println(outcome.getMessage());
    }

    /* Print the statistics. */
    private void printStats() {
        int wins = game.getWins();
        int losses = game.getLosses();
        int ties = game.getTies();

        System.out.format("Wins: %d  Losses: %d  Ties: %d", wins, losses, ties);
        System.out.println();
        System.out.println();
    }
}
