package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;

/*
 * This strategy examines the opponent's history to find his most frequent move
 * and predicts that he will choose it.
 */
public class FrequencyStrategy extends Strategy {
    private Shape[] shapes;
    private int[] frequencies;

    public FrequencyStrategy() {
        shapes = Shape.values();
        frequencies = new int[shapes.length];
    }

    /* Record the frequencies of each kind of move the opponent made */
    @Override
    public void addHistory(Shape me, Shape opponent) {
        frequencies[opponent.ordinal()]++;
    }

    /*
     * Return the most frequent move as the prediction. Return good old rock if
     * there is no history yet.
     * */
    @Override
    protected Shape predict() {
        Shape shape = Shape.ROCK;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < frequencies.length; i++)
            if (frequencies[i] > max) {
                shape = shapes[i];
                max = frequencies[i];
            }

        return shape;
    }
}
