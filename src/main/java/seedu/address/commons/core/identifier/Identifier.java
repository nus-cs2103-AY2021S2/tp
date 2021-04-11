package seedu.address.commons.core.identifier;

/**
 * Represents a one-based identifier for the events
 *
 * {@code Identifier} should be used right from the start (when parsing in a new user input), so that if the current
 * component wants to communicate with another component, it can send an {@code Identifier} to avoid having to know what
 * base the other component is using for its identifier. However, after receiving the {@code Identifier},
 * that component can convert it back to an int if the identifier will not be passed to a different component again.
 */
public class Identifier {
    private final int value;

    /**
     * Identifier can only be created by calling {@link Identifier#fromIdentifier(int)} or
     */
    Identifier(int value) {
        if (value <= 0) {
            throw new IndexOutOfBoundsException();
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getOneBased() {
        if (value <= 0) {
            throw new IndexOutOfBoundsException();
        }
        return value;
    }

    public int getZeroBased() {
        if (value < 0) {
            throw new IndexOutOfBoundsException();
        }
        return value - 1;
    }

    /**
     * Creates a new {@code Identfier} using a value.
     */
    public static Identifier fromIdentifier(int value) {
        return new Identifier(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Identifier // instanceof handles nulls
                && this.value == ((Identifier) other).value); // state check
    }
}
