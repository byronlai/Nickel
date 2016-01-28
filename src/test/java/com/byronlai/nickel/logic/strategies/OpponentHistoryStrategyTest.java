package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/* Test cases for OpponentHistoryStrategy. */
public class OpponentHistoryStrategyTest {
    /*
     * If the last 3 moves the opponent made occurred in the past, this strategy
     * will assume that the opponent will play the move after that repetition.
     */
    @Test
    public void shouldReturnNextMoveWhenPatternFound() {
        OpponentHistoryStrategy strategy = new OpponentHistoryStrategy();
        strategy.addHistory(Shape.ROCK, Shape.LIZARD);
        strategy.addHistory(Shape.ROCK, Shape.PAPER);
        strategy.addHistory(Shape.ROCK, Shape.SCISSORS);
        strategy.addHistory(Shape.ROCK, Shape.LIZARD);
        strategy.addHistory(Shape.ROCK, Shape.PAPER);
        strategy.addHistory(Shape.ROCK, Shape.SCISSORS);
        Shape[] nextMoves = strategy.getNextMoves();
        assertTrue(Arrays.equals(nextMoves, Shape.LIZARD.defeatedBy()));
    }

    /*
     * If the last sequence of moves the opponent made does not repeat in the
     * history, this strategy will assume that the opponent will play rock.
     */
    @Test
    public void shouldDefeatRockWhenPatternNotFound() {
        OpponentHistoryStrategy strategy = new OpponentHistoryStrategy();
        strategy.addHistory(Shape.ROCK, Shape.PAPER);
        strategy.addHistory(Shape.ROCK, Shape.LIZARD);
        strategy.addHistory(Shape.ROCK, Shape.SCISSORS);
        Shape[] nextMoves = strategy.getNextMoves();
        assertTrue(Arrays.equals(nextMoves, Shape.ROCK.defeatedBy()));
    }
}
