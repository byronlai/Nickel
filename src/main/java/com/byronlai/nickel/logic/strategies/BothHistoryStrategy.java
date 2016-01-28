package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;

/*
 * This strategy predicts the opponent's next move by finding repeating patterns
 * in the opponent's history and our history. For example, if the last 4 moves
 * we made are rock, paper, scissors and lizard then we find the location in
 * the past when these 4 moves occurred. This strategy assumes that the opponent
 * will play the move after that location.
 */
public class BothHistoryStrategy extends Strategy {
    private class Pair {
        Shape me;
        Shape opponent;

        public Pair(Shape me, Shape opponent) {
            this.me = me;
            this.opponent = opponent;
        }

        @Override
        public boolean equals(Object obj) {
            Pair pair = (Pair) obj;
            return me == pair.me && opponent == pair.opponent;
        }
    }

    private History<Pair> history;

    public BothHistoryStrategy() {
        history = new History<Pair>();
    }

    /* Record the moves we and the opponent made in each round. */
    @Override
    public void addHistory(Shape me, Shape opponent) {
        history.add(new Pair(me, opponent));
    }

    /* Predict by finding repeating patterns in the past. */
    @Override
    protected Shape predict() {
        return history.match(new Pair(Shape.ROCK, Shape.ROCK)).opponent;
    }
}
