package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's name in Focuris
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class EventName {

    public static final String MESSAGE_CONSTRAINTS =
            "Event names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String eventName;

    /**
     * Constructs a {@code EventName}.
     *
     * @param eventName A valid event name.
     */
    public EventName(String eventName) {
        requireNonNull(eventName);
        checkArgument(isValidName(eventName), MESSAGE_CONSTRAINTS);
        this.eventName = eventName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return this.eventName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventName // instanceof handles nulls
                && this.eventName.equalsIgnoreCase(((EventName) other).eventName)); // state check
    }

    @Override
    public int hashCode() {
        return this.eventName.hashCode();
    }

}
