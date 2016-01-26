package com.byronlai.nickel.logic;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/* Test cases for Shape. */
public class ShapeTest {
    /* Rock should be defeated by spock and paper. */
    @Test
    public void testDefeatedBy() {
        Shape[] shapes = {Shape.SPOCK, Shape.PAPER};
        assertTrue(Arrays.equals(shapes, Shape.ROCK.defeatedBy()));
    }

    /*
     * Rock should be defeated by spock and paper but not lizard, scissors and
     * rock itself.
     */
    @Test
    public void testIsDefeatedBy() {
        assertTrue(Shape.ROCK.isDefeatedBy(Shape.SPOCK));
        assertTrue(Shape.ROCK.isDefeatedBy(Shape.PAPER));
        assertFalse(Shape.ROCK.isDefeatedBy(Shape.LIZARD));
        assertFalse(Shape.ROCK.isDefeatedBy(Shape.SCISSORS));
        assertFalse(Shape.ROCK.isDefeatedBy(Shape.ROCK));
    }

    /*
     * Rock should defeat lizard and scissors but not paper, spock and rock
     * itself.
     */
    @Test
    public void testDefeats() {
        assertTrue(Shape.ROCK.defeats(Shape.LIZARD));
        assertTrue(Shape.ROCK.defeats(Shape.SCISSORS));
        assertFalse(Shape.ROCK.defeats(Shape.PAPER));
        assertFalse(Shape.ROCK.defeats(Shape.SPOCK));
        assertFalse(Shape.ROCK.defeats(Shape.ROCK));
    }
}
