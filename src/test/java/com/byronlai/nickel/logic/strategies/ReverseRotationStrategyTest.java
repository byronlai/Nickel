package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/* Test cases for ReverseRotationStrategy. */
public class ReverseRotationStrategyTest {
    /*
     * ReverseRotationStrategy predicts by rotating the opponent's last move to
     * the left. If the last move is paper, it will predict rock so we should
     * play lizard or paper. If the last move is rock, it will predict spock so
     * we should play paper or lizard.
     */
    @Test
    public void shouldPredictByRotatingPreviousMoveInReverseDirection() {
        ReverseRotationStrategy strategy = new ReverseRotationStrategy();

        strategy.addHistory(Shape.ROCK, Shape.PAPER);
        assertTrue(Arrays.equals(strategy.getNextMoves(), Shape.ROCK.defeatedBy()));

        strategy.addHistory(Shape.ROCK, Shape.ROCK);
        assertTrue(Arrays.equals(strategy.getNextMoves(), Shape.SPOCK.defeatedBy()));
    }
}
