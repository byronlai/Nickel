package com.byronlai.nickel.logic.strategies;

import org.junit.Test;
import com.byronlai.nickel.logic.Shape;
import static org.junit.Assert.assertEquals;

/* Test cases for SwapStrategy. */
public class SwapStrategyTest {
    /*
     * Assume the opponent uses frequency counting against us. SwapStrategy
     * exchanges the position of us and our opponent. If frequency counting
     * predicts that we will play paper, the opponent will play scissors or
     * lizard so we should play rock instead.
     */
    @Test
    public void shouldPutInOpponentShoes() {
        FrequencyStrategy frequencyStrategy = new FrequencyStrategy();
        SwapStrategy swapStrategy = new SwapStrategy(frequencyStrategy);
        swapStrategy.addHistory(Shape.PAPER, Shape.SCISSORS);
        Shape[] nextMoves = swapStrategy.getNextMoves();
        assertEquals(Shape.ROCK, nextMoves[0]);
        assertEquals(Shape.ROCK, nextMoves[1]);
    }
}
