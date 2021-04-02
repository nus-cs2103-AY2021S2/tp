package seedu.dictionote.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class FrequencyCounterTest {
    @Test
    public void construct_counterWithoutInitialValue_success() {
        FrequencyCounter fc = new FrequencyCounter();
        assertNotNull(fc);
        assertEquals(fc, new FrequencyCounter());
    }

    @Test
    public void construct_counterWithInitialNonNegativeValue_success() {
        FrequencyCounter fc = new FrequencyCounter(9);
        assertNotNull(fc);
        assertEquals(fc, new FrequencyCounter(9));
    }

    @Test
    public void construct_counterWithInitialNegativeValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new FrequencyCounter(-7);
            }
        });
    }

    @Test
    public void equals() {
        // Counters without initial values.
        assertEquals(new FrequencyCounter(), new FrequencyCounter());

        // One counter without an initial value, and another with an initial value of 0.
        assertEquals(new FrequencyCounter(), new FrequencyCounter(0));

        // Counters with equal initial values
        assertEquals(new FrequencyCounter(1), new FrequencyCounter(1));

        // Counters with different initial values
        assertNotEquals(new FrequencyCounter(0), new FrequencyCounter(1));

        // Counter against a different type.
        assertNotEquals(new FrequencyCounter(), "FrequencyCounter");

        // Counter against null.
        assertNotEquals(new FrequencyCounter(), null);
    }

    @Test
    public void increment_success() {
        FrequencyCounter fc = new FrequencyCounter();

        assertEquals(fc.increment(), new FrequencyCounter(1));
        assertEquals(incrementCounterManyTimes(fc, 5), new FrequencyCounter(5));
    }

    /**
     * Returns a frequency counter that results from incrementing the given frequency counter n-times.
     */
    private static FrequencyCounter incrementCounterManyTimes(FrequencyCounter fc, int n) {
        for (int i = 0; i < n; i++) {
            fc = fc.increment();
        }

        return fc;
    }

    @Test
    public void validity_zeroFrequencyCounterValue_true() {
        // Boundary value for FrequencyCounter#isValidFrequencyCounter(int): 0.
        assertTrue(FrequencyCounter.isValidFrequencyCounter(0));
    }

    @Test
    public void validity_positiveFrequencyCounterValue_true() {
        // Boundary value for FrequencyCounter#isValidFrequencyCounter(int): 1.
        assertTrue(FrequencyCounter.isValidFrequencyCounter(1));
    }

    @Test
    public void validity_negativeFrequencyCounterValue_false() {
        // Boundary value for FrequencyCounter#isValidFrequencyCounter(int): -1.
        assertFalse(FrequencyCounter.isValidFrequencyCounter(-1));
    }
}
