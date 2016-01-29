package com.byronlai.nickel.logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/* Test cases for Game */
public class GameTest {
    /*
     * Initially, no games have been played. All the statistics (wins, losses,
     * ties) should be zero.
     */
    @Test
    public void shouldReturn0ForAllStatsInitially() {
        Game game = new Game();
        assertEquals(0, game.getWins());
        assertEquals(0, game.getTies());
        assertEquals(0, game.getLosses());
    }

    /*
     * After playing several rounds, return non-zero statistics (wins, losses,
     * ties).
     */
    @Test
    public void shouldReturnCorrectStats() {
        Game game = new Game();
        int wins = 0;
        int ties = 0;
        int losses = 0;

        for (Shape opponent : Shape.values()) {
            GameResult result = game.play(opponent);
            Outcome outcome = result.getOutcome();
            Shape me = result.getComputerChoice();

            if (me == opponent) {
                ties++;
                assertEquals(Outcome.TIE, outcome);
            } else if (me.isDefeatedBy(opponent)) {
                wins++;
                assertEquals(Outcome.WIN, outcome);
            } else {
                losses++;
                assertEquals(Outcome.LOSE, outcome);
            }
        }

        assertEquals(wins, game.getWins());
        assertEquals(ties, game.getTies());
        assertEquals(losses, game.getLosses());
    }
}
