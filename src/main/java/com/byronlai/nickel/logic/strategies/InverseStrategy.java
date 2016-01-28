package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;

/*
 * This strategy is used to defeat second-guessing, triple-guessing and so on.
 * Assume the opponent predicts that we will use the given strategy. If the
 * given strategy predicts rock, he will know that our next move will be paper
 * or spock so he will play lizard. Therefore we should play rock or scissors
 * instead.
 */
public class InverseStrategy extends Strategy {
    private Strategy strategy;

    public InverseStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /* Forward the call to the wrapped strategy. */
    @Override
    public void addHistory(Shape me, Shape opponent) {
        strategy.addHistory(me, opponent);
    }

    /* Return the move that defeats the wrapped strategy. */
    @Override
    protected Shape predict() {
        Shape[] nextMoves = strategy.getNextMoves();

        for (Shape shape : Shape.values())
            if (shape.defeats(nextMoves[0]) && shape.defeats(nextMoves[1]))
                return shape;

        return Shape.ROCK;
    }
}
