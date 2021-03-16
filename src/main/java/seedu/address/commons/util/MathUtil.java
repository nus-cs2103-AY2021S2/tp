package seedu.address.commons.util;

/**
 * Helper functions for common math operations.
 */
public class MathUtil {
    /**
     * To prevent instantiation.
     */
    private MathUtil() {
    }

    /**
     * Clamps a value between an inclusive upper and lower bound.
     *
     * Returns {@code min} if {@code value} < {@code min}. Returns {@code max} if {@code value} > {@code max}.
     * Otherwise, returns {@code value}.
     *
     * @param value The value to clamp.
     * @param min The inclusive lower bound.
     * @param max The inclusive upper bound.
     * @return The clamped value.
     */
    public static int clamp(int value, int min, int max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }
}
