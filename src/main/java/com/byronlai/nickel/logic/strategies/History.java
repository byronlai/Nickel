package com.byronlai.nickel.logic.strategies;

import java.util.ArrayList;

/* Data structure for recording the moves made so far. */
class History<T> extends ArrayList<T> {
    /*
     * Find the most recent time that the last sequence of moves have been
     * played and return the move after that. Return the given default value if
     * no repetition is found.
     */
    public T match(T defaultValue) {
        for (int length = 20; length >= 1; length--) {
            int right = size() - length;

            for (int left = right - length; left >= 0; left--) {
                boolean exist = true;

                for (int i = 0; i < length; i++)
                    if (!get(left + i).equals(get(right + i))) {
                        exist = false;
                        break;
                    }

                if (exist)
                    return get(left + length);
            }
        }

        return defaultValue;
    }
}
