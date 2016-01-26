package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;
import java.util.Random;

/*
 * A strategy is an algorithm used to predict the opponent's next move. The
 * moves made by the two players so far are provided. Depending on the
 * algorithm, the strategy can make use of the history or simply ignore it.
 */
public abstract class Strategy {
    private Random random;

    public Strategy() {
        random = new Random();
    }

    /* Provide the previous moves made by us and the opponent. */
    public abstract void addHistory(Shape me, Shape opponent);

    /* Predict the opponent's next move. */
    protected abstract Shape predict();

    /* Get our next moves based on our prediction. */
    public Shape[] getNextMoves() {
        return predict().defeatedBy();
    }

    /* Choose one move randomly from getNextMoves(). */
    public Shape getNextMove() {
        return getNextMoves()[random.nextInt(2)];
    }
}
