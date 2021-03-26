package seedu.dictionote.model.contact;

import static seedu.dictionote.commons.util.AppUtil.checkArgument;

/**
 * Represents a Contact's frequency counter in the contacts list.
 * Guarantees: immutable; is valid as declared in {@link #isValidFrequencyCounter(int)}
 */
public class FrequencyCounter {

    public static final String MESSAGE_CONSTRAINTS = "Frequency counter values should be non-negative";

    public final int value;

    /**
     * Constructs a {@code FrequencyCounter} with its value set to 0.
     */
    public FrequencyCounter() {
        this(0);
    }

    /**
     * Constructs a {@code FrequencyCounter}.
     *
     * @param frequencyCounter A valid frequency counter value.
     */
    public FrequencyCounter(int frequencyCounter) {
        checkArgument(isValidFrequencyCounter(frequencyCounter), MESSAGE_CONSTRAINTS);
        value = frequencyCounter;
    }

    public FrequencyCounter increment() {
        return new FrequencyCounter(value + 1);
    }

    /**
     * Returns true if a given integer is a valid frequency counter value.
     */
    public static boolean isValidFrequencyCounter(int test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return String.format("%d", value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FrequencyCounter // instanceof handles nulls
                && value == ((FrequencyCounter) other).value); // state check
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
