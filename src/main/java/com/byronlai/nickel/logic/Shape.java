package com.byronlai.nickel.logic;

/*
 * Represent one of the five shapes each player forms when playing rock paper
 * scissors lizard Spock.
 */
public enum Shape {
    ROCK,
    PAPER,
    SCISSORS,
    LIZARD,
    SPOCK;

    /* Return the shapes that defeat this shape. */
    public Shape[] defeatedBy() {
        Shape[] shapes = new Shape[2];

        switch (this) {
            case ROCK:
                shapes[0] = Shape.SPOCK;
                shapes[1] = Shape.PAPER;
                break;
            case PAPER:
                shapes[0] = Shape.LIZARD;
                shapes[1] = Shape.SCISSORS;
                break;
            case SCISSORS:
                shapes[0] = Shape.SPOCK;
                shapes[1] = Shape.ROCK;
                break;
            case LIZARD:
                shapes[0] = Shape.ROCK;
                shapes[1] = Shape.SCISSORS;
                break;
            case SPOCK:
                shapes[0] = Shape.LIZARD;
                shapes[1] = Shape.PAPER;
                break;
        }

        return shapes;
    }

    /* Return true if the given shape defeats this shape. */
    public boolean isDefeatedBy(Shape shape) {
        Shape[] responses = defeatedBy();
        return responses[0] == shape || responses[1] == shape;
    }

    /* Return true if this shape defeats the given shape. */
    public boolean defeats(Shape shape) {
        return shape.isDefeatedBy(this);
    }
}
