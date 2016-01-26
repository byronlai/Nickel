package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/* Test cases for FixedMoveStrategy. */
public class FixedMoveStrategyTest {
    /*
     * This strategy should always return the same prediction. If the fixed move
     * is lizard, it will predict lizard and we should play rock or scissors.
     */
    @Test
    public void shouldReturnSamePrediction() {
        FixedMoveStrategy strategy = new FixedMoveStrategy(Shape.LIZARD);
        Shape[] nextMoves = strategy.getNextMoves();
        assertTrue(Arrays.equals(nextMoves, Shape.LIZARD.defeatedBy()));
    }
}
