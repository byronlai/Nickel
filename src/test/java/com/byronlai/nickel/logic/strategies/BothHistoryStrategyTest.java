package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/* Test cases for BothHistoryStrategy. */
public class BothHistoryStrategyTest {
    /*
     * If the last sequence of moves we and the opponent made occurred in the
     * past, this strategy will assume that the opponent will play the move
     * after that repetition.
     */
    @Test
    public void shouldReturnNextMoveWhenPatternFound() {
        BothHistoryStrategy strategy = new BothHistoryStrategy();
        strategy.addHistory(Shape.ROCK, Shape.LIZARD);
        strategy.addHistory(Shape.SPOCK, Shape.PAPER);
        strategy.addHistory(Shape.ROCK, Shape.SCISSORS);
        strategy.addHistory(Shape.ROCK, Shape.LIZARD);
        strategy.addHistory(Shape.SPOCK, Shape.PAPER);
        Shape[] nextMoves = strategy.getNextMoves();
        assertTrue(Arrays.equals(nextMoves, Shape.SCISSORS.defeatedBy()));
    }

    /*
     * If the last sequence of moves we and the opponent made is not repeated
     * in the history, this strategy will assume that the opponent will play
     * rock.
     */
    @Test
    public void shouldDefeatRockWhenPatternNotFound() {
        BothHistoryStrategy strategy = new BothHistoryStrategy();
        strategy.addHistory(Shape.ROCK, Shape.LIZARD);
        strategy.addHistory(Shape.SPOCK, Shape.PAPER);
        strategy.addHistory(Shape.SPOCK, Shape.LIZARD);
        Shape[] nextMoves = strategy.getNextMoves();
        assertTrue(Arrays.equals(nextMoves, Shape.ROCK.defeatedBy()));
    }
}
