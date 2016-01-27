package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/* Test cases for FrequencyStrategy. */
public class FrequencyStrategyTest {
    /*
     * When no moves have been made yet, the frequencies of all possible moves
     * are zero. In the case, this strategy should predict rock.
     */
    @Test
    public void shouldDefeatRockWhenNoHistory() {
        FrequencyStrategy strategy = new FrequencyStrategy();
        Shape[] nextMoves = strategy.getNextMoves();
        assertTrue(Arrays.equals(nextMoves, Shape.ROCK.defeatedBy()));
    }

    /*
     * If the most frequent move is lizard, this strategy should predict lizard
     * and we should play rock or scissors.
     */
    @Test
    public void shouldDefeatMostFrequentMove() {
        FrequencyStrategy strategy = new FrequencyStrategy();
        strategy.addHistory(Shape.ROCK, Shape.PAPER);
        strategy.addHistory(Shape.ROCK, Shape.LIZARD);
        strategy.addHistory(Shape.ROCK, Shape.LIZARD);
        Shape[] nextMoves = strategy.getNextMoves();
        assertTrue(Arrays.equals(nextMoves, Shape.LIZARD.defeatedBy()));
    }
}
