package com.byronlai.nickel.logic;

/* Represent the states of a tournament. */
public class Game {
    private Bot bot;
    private int wins;
    private int losses;
    private int ties;

    public Game() {
        bot = new Bot();
    }

    /* Return the number of times the player wins. */
    public int getWins() {
        return wins;
    }

    /* Return the number of times the player loses. */
    public int getLosses() {
        return losses;
    }

    /* Return the number of times that the game was a tie. */
    public int getTies() {
        return ties;
    }

    /* Given the player's move, play another round of RPSLV. */
    public GameResult play(Shape userChoice) {
        Shape computerChoice = bot.getNextMove();
        bot.addHistory(computerChoice, userChoice);
        Outcome outcome = Outcome.determine(computerChoice, userChoice);

        if (outcome == Outcome.WIN)
            wins++;
        else if (outcome == Outcome.LOSE)
            losses++;
        else
            ties++;

        return new GameResult(computerChoice, outcome);
    }
}
