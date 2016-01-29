package com.byronlai.nickel.logic;

import com.byronlai.nickel.logic.Shape;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/* Test cases for Bot */
public class BotTest {
    /*
     * When the opponent's next move is always the right rotation of his last
     * move, RotationStrategy should be used.
     */
    @Test
    public void shouldPreferRotationStrategy() {
        Bot bot = new Bot();
        bot.addHistory(Shape.ROCK, Shape.ROCK);
        bot.addHistory(Shape.ROCK, Shape.PAPER);
        bot.addHistory(Shape.ROCK, Shape.SCISSORS);
        bot.addHistory(Shape.ROCK, Shape.LIZARD);
        assertTrue(bot.getNextMove().defeats(Shape.SPOCK));
    }

    /*
     * When the opponent's next move is always the left rotation of his last
     * move, ReverseRotationStrategy should be used.
     */
    @Test
    public void shouldPreferReverseRotationStrategy() {
        Bot bot = new Bot();
        bot.addHistory(Shape.LIZARD, Shape.SPOCK);
        bot.addHistory(Shape.PAPER, Shape.LIZARD);
        bot.addHistory(Shape.SCISSORS, Shape.SCISSORS);
        bot.addHistory(Shape.ROCK, Shape.PAPER);
        assertTrue(bot.getNextMove().defeats(Shape.ROCK));
    }

    /*
     * When the opponent always plays the same move, FixedMoveStrategy should be
     * used.
     */
    @Test
    public void shouldPreferFixedMoveStrategy() {
        Bot bot = new Bot();

        for (int i = 0; i < 10; i++)
            bot.addHistory(Shape.PAPER, Shape.SPOCK);

        assertTrue(bot.getNextMove().defeats(Shape.SPOCK));
    }

    /*
     * When the opponent usually plays rock, FrequencyStrategy should be used.
     */
    @Test
    public void shouldPreferFrequencyStrategy() {
        Bot bot = new Bot();

        for (int i = 0; i < 10; i++)
            bot.addHistory(Shape.SPOCK, Shape.ROCK);

        assertTrue(bot.getNextMove().defeats(Shape.ROCK));
    }

    /*
     * If the opponent tends to repeat his moves, OpponentHistoryStrategy should
     * be used.
     */
    @Test
    public void shouldPreferOpponentHistoryStrategy() {
        Bot bot = new Bot();

        for (int i = 0; i < 20; i++) {
            bot.addHistory(Shape.ROCK, Shape.PAPER);
            bot.addHistory(Shape.ROCK, Shape.SCISSORS);
        }

        assertTrue(bot.getNextMove().defeats(Shape.PAPER));
    }

    /*
     * If the sequence of moves tends to repeat itself, BothHistoryStrategy
     * should be used.
     */
    @Test
    public void shouldPreferBothHistoryStrategy() {
        Bot bot = new Bot();

        for (int i = 0; i < 20; i++) {
            bot.addHistory(Shape.LIZARD, Shape.PAPER);
            bot.addHistory(Shape.SPOCK, Shape.SCISSORS);
            bot.addHistory(Shape.LIZARD, Shape.ROCK);
        }

        assertTrue(bot.getNextMove().defeats(Shape.PAPER));
    }

    /*
     * If the opponent uses the rotation strategy against us, SwapStrategy
     * should be used.
     */
    @Test
    public void shouldPreferSwapRotationStrategy() {
        Bot bot = new Bot();
        bot.addHistory(Shape.ROCK, Shape.ROCK);
        bot.addHistory(Shape.ROCK, Shape.SCISSORS);
        bot.addHistory(Shape.ROCK, Shape.LIZARD);
        bot.addHistory(Shape.ROCK, Shape.SCISSORS);
        bot.addHistory(Shape.ROCK, Shape.SCISSORS);

        Shape nextMove = bot.getNextMove();
        assertTrue(nextMove.defeats(Shape.LIZARD));
        assertTrue(nextMove.defeats(Shape.SCISSORS));
    }

    /*
     * If the opponent is second-guessing us, InverseStrategy should be used.
     */
    @Test
    public void shouldPreferInverseFrequencyStrategy() {
        Bot bot = new Bot();
        bot.addHistory(Shape.LIZARD, Shape.ROCK);
        bot.addHistory(Shape.PAPER, Shape.LIZARD);
        bot.addHistory(Shape.SPOCK, Shape.LIZARD);
        bot.addHistory(Shape.ROCK, Shape.SPOCK);
        bot.addHistory(Shape.SCISSORS, Shape.SPOCK);
        assertTrue(bot.getNextMove().defeats(Shape.SPOCK));
    }
}
