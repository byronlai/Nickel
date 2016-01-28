package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/* Test cases for InverseStrategy. */
public class InverseStrategyTest {
    /*
     * Assume the opponent predicts that we will use frequency counting against
     * him. If the strategy predicts rock, he will know that our next move will
     * be paper or spock so he will play lizard. Therefore we should play rock
     * or scissors instead.
     */
    @Test
    public void shouldDefeatSecondGuessing() {
        FrequencyStrategy frequencyStrategy = new FrequencyStrategy();
        frequencyStrategy.addHistory(Shape.ROCK, Shape.ROCK);
        InverseStrategy inverseStrategy = new InverseStrategy(frequencyStrategy);
        Shape[] nextMoves = inverseStrategy.getNextMoves();
        assertTrue(Arrays.equals(nextMoves, Shape.LIZARD.defeatedBy()));
    }
}
