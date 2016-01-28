package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;

/*
 * Assume the opponent uses the given strategy against us. This strategy
 * exchanges the position of us and our opponent. If the given strategy predicts
 * that we will play rock, the opponent will play paper or spock so we should
 * play lizard instead.
 */
public class SwapStrategy extends Strategy {
    private Strategy strategy;

    public SwapStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /* Exchange the position of us and the opponent. */
    @Override
    public void addHistory(Shape me, Shape opponent) {
        strategy.addHistory(opponent, me);
    }

    /* This operation is not supported. */
    @Override
    protected Shape predict() {
        throw new UnsupportedOperationException();
    }

    /*
     * Assume the opponent uses the given strategy against us. Return moves that
     * defeat that strategy.
     */
    @Override
    public Shape[] getNextMoves() {
        Shape[] opponentNextMoves = strategy.getNextMoves();
        Shape[] nextMoves = new Shape[2];

        for (Shape shape : Shape.values())
            if (shape.defeats(opponentNextMoves[0]) && shape.defeats(opponentNextMoves[1]))
                nextMoves[0] = nextMoves[1] = shape;

        return nextMoves;
    }
}
