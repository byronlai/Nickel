package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;

/*
 * This strategy predicts the opponent's next move by assuming that he will
 * choose his next move by rotating his last move to the left.
 */
public class ReverseRotationStrategy extends Strategy {
    private Shape[] shapes;
    private Shape last;

    public ReverseRotationStrategy() {
        shapes = Shape.values();
    }

    /* Record the last move made by the opponent. */
    @Override
    public void addHistory(Shape me, Shape opponent) {
        last = opponent;
    }

    /* Rotate the last move to the left. Return rock if there is no history. */
    @Override
    protected Shape predict() {
        if (last == null)
            return Shape.ROCK;

        return shapes[(last.ordinal() - 1 + shapes.length) % shapes.length];
    }
}
