package com.byronlai.nickel.logic.strategies;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/* Test cases for History. */
public class HistoryTest {
    /*
     * If a sequence in the past matches the last few items, return the item
     * following that match.
     */
    @Test
    public void shouldReturnNextEntryWhenPatternFound() {
        History<Integer> history = new History<Integer>();
        history.add(1);
        history.add(2);
        history.add(3);
        history.add(1);
        history.add(2);
        assertEquals(3, history.match(0).intValue());
    }

    /*
     * If no sequence in the past matches the last few items, return the given
     * default value.
     */
    @Test
    public void shouldReturnDefaultWhenNoPatternFound() {
        History<Integer> history = new History<Integer>();
        history.add(1);
        history.add(2);
        history.add(3);
        assertEquals(0, history.match(0).intValue());
    }
}
