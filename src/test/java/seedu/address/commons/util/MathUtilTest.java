package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for MathUtil's functions.
 */
public class MathUtilTest {
    @Test
    public void clamp() {
        final int upper = 3478;
        final int lower = -9812;
        final int value = 123;

        // EP: Value strictly below lower bound -> returns lower bound
        assertEquals(lower, MathUtil.clamp(lower - 213, lower, upper));

        // EP: Value strictly above upper bound -> returns upper bound
        assertEquals(upper, MathUtil.clamp(upper + 91, lower, upper));

        // EP: Value strictly between bounds -> returns value
        assertEquals(value, MathUtil.clamp(value, lower, upper));

        // BVA: Bounds and value equal -> returns value
        assertEquals(upper, MathUtil.clamp(upper, upper, upper));

        // BVA: Value equals upper bound only -> returns value
        assertEquals(upper, MathUtil.clamp(upper, lower, upper));

        // BVA: Value one below upper bound -> returns value
        assertEquals(upper - 1, MathUtil.clamp(upper - 1, lower, upper));

        // BVA: Value one above upper bound -> returns upper bound
        assertEquals(upper, MathUtil.clamp(upper + 1, lower, upper));

        // BVA: Value equals lower bound only -> returns value
        assertEquals(lower, MathUtil.clamp(lower, lower, upper));

        // BVA: Value one above lower bound -> returns value
        assertEquals(lower + 1, MathUtil.clamp(lower + 1, lower, upper));

        // BVA: Value one below lower bound -> returns lower bound
        assertEquals(lower, MathUtil.clamp(lower - 1, lower, upper));
    }

    @Test
    public void clamp_givenMinGreaterThanMax_throwsIllegalArgumentException() {
        final int min = 5;
        final int max = -7;
        final int value = 23;

        assertThrows(IllegalArgumentException.class, () -> MathUtil.clamp(value, min, max));
    }
}
