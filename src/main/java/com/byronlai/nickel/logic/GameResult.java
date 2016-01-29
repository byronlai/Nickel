package com.byronlai.nickel.logic;

/*
 * Represent the result of a game. The result includes the move that the bot
 * made and the outcome (win, loss or tie).
 */
public class GameResult {
    private Shape computerChoice;
    private Outcome outcome;

    public GameResult(Shape computerChoice, Outcome outcome) {
        this.computerChoice = computerChoice;
        this.outcome = outcome;
    }

    /* Get the move that the bot made. */
    public Shape getComputerChoice() {
        return computerChoice;
    }

    /* Get the outcome of the game. */
    public Outcome getOutcome() {
        return outcome;
    }
}
