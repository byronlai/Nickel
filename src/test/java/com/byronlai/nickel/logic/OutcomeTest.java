package com.byronlai.nickel.logic;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/* Test cases for Outcome. */
public class OutcomeTest {
    /*
     * Paper covers rock. If both players throw the same shape, the game is
     * tied.
     */
    @Test
    public void testDetermine() {
        assertEquals(Outcome.WIN, Outcome.determine(Shape.ROCK, Shape.PAPER));
        assertEquals(Outcome.TIE, Outcome.determine(Shape.ROCK, Shape.ROCK));
        assertEquals(Outcome.LOSE, Outcome.determine(Shape.PAPER, Shape.ROCK));
    }

    /* Verify the message shown to the player. */
    @Test
    public void testGetMessage() {
        assertEquals("You won the game!", Outcome.WIN.getMessage());
        assertEquals("The game was a tie!", Outcome.TIE.getMessage());
        assertEquals("You lost the game!", Outcome.LOSE.getMessage());
    }
}
