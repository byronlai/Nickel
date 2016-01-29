package com.byronlai.nickel.logic;

/* Represent the three possible outcomes of a game. */
public enum Outcome {
    WIN,
    LOSE,
    TIE;

    /* Determine the outcome given the two shapes that the players form. */
    public static Outcome determine(Shape me, Shape opponent) {
        if (me == opponent)
            return Outcome.TIE;

        if (opponent.isDefeatedBy(me))
            return Outcome.LOSE;

        return Outcome.WIN;
    }

    /* Get the message representing the outcome. */
    public String getMessage() {
        if (this == Outcome.WIN)
            return "You won the game!";
        else if (this == Outcome.LOSE)
            return "You lost the game!";
        else
            return "The game was a tie!";
    }
}
