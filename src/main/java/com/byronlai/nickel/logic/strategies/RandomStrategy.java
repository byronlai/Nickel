package com.byronlai.nickel.logic.strategies;

import com.byronlai.nickel.logic.Shape;
import java.util.Random;

/*
 * This strategy predicts the opponent's next move by randomly choosing a shape.
 * When combining with SwapStrategy, this strategy can prevent us from having a
 * devastating loss because there is no way to guess our next move if we choose
 * at random.
 */
public class RandomStrategy extends Strategy {
    private Shape[] shapes;
    private Random random;

    public RandomStrategy() {
        shapes = Shape.values();
        random = new Random();
    }

    /* This strategy does not use the history. */
    @Override
    public void addHistory(Shape me, Shape opponent) {}

    /* Return a move randomly. */
    @Override
    protected Shape predict() {
        return shapes[random.nextInt(shapes.length)];
    }
}
