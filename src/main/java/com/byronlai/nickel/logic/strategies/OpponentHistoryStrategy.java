package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;

/*
 * This strategy predicts the opponent's next move by finding repeating patterns
 * in the opponent's history. For example, if the last 3 moves the opponent made
 * are rock, paper and scissors, then we find the location in the past when
 * these 3 moves occur. This strategy assumes that the opponent will play the
 * move after that location.
 */
public class OpponentHistoryStrategy extends Strategy {
    private History<Shape> history;

    public OpponentHistoryStrategy() {
        history = new History<Shape>();
    }

    /* Record the moves the opponent made in each round. */
    @Override
    public void addHistory(Shape me, Shape opponent) {
        history.add(opponent);
    }

    /* Predict by finding repeating patterns in the past. */
    @Override
    protected Shape predict() {
        return history.match(Shape.ROCK);
    }
}
