package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/* Test cases for RotationStrategy. */
public class RotationStrategyTest {
    /*
     * RotationStrategy predicts by rotating the opponent's last move to the
     * right. If the last move is paper, it will predict scissors so we should
     * play rock or spock. If the last move is spock, it will predict rock so
     * we should play paper or spock.
     */
    @Test
    public void shouldPredictByRotatingPreviousMove() {
        RotationStrategy strategy = new RotationStrategy();

        strategy.addHistory(Shape.ROCK, Shape.PAPER);
        assertTrue(Arrays.equals(strategy.getNextMoves(), Shape.SCISSORS.defeatedBy()));

        strategy.addHistory(Shape.ROCK, Shape.SPOCK);
        assertTrue(Arrays.equals(strategy.getNextMoves(), Shape.ROCK.defeatedBy()));
    }
}
