package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a Date event for a Person in the FriendDex. The event must have occurred already.
 */
public abstract class Event {
    public static final String DESCRIPTION_MESSAGE_CONSTRAINTS =
            "Description can take any values, and it should not be blank";
    public static final String DESCRIPTION_VALIDATION_REGEX = "[^\\s].*";
    protected final LocalDate date;
    protected final LocalTime time;
    protected final String description;

    /**
     * Constructs a {@code Event}
     *
     * @param date        A valid date.
     * @param description A description of the event.
     */
    public Event(LocalDate date, String description) {
        requireAllNonNull(date, description);
        this.date = date;
        this.time = null;
        this.description = description;
    }

    /**
     * Constructs a {@code Event}
     *
     * @param date        A valid date.
     * @param time        A valid time.
     * @param description A description of the event.
     */
    public Event(LocalDate date, LocalTime time, String description) {
        requireAllNonNull(date, time, description);
        this.date = date;
        this.time = time;
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(DESCRIPTION_VALIDATION_REGEX);
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasTime() {
        return time != null;
    }

    /**
     * Returns the string to be displayed on the UI
     *
     * @return String to be displayed on the UI
     */
    public abstract String toUi();
}
