package seedu.partyplanet.model.event;

import seedu.partyplanet.model.date.Date;

/** Represents an Event's date in PartyPlanet.
 * Guarantees: immutable; is always valid.
 */
public class EventDate extends Date {

    public static final String MESSAGE_CONSTRAINTS = "Event dates should be in one of the following formats:\n"
            + Date.MESSAGE_CONSTRAINTS;

    public static final EventDate EMPTY_EVENT_DATE = new EventDate();

    /**
     * Constructs a {@code EventDate}.
     * EventDate can optionally contain a year.
     * Some invalid dates are mapped to the nearest valid date, e.g. 29 Feb 2021 -> 28 Feb 2021.
     *
     * @param eventDate A valid eventDate.
     */
    public EventDate(String eventDate) {
        super(eventDate, true);
    }

    /**
     * Constructs an empty eventDate.
     */
    public EventDate() {
        super();
    }

    /**
     * Returns true if a given event date string is a valid date.
     */
    public static boolean isValidEventDate(String test) {
        return isValidDate(test);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EventDate)) {
            return false;
        }
        if (isEmpty == EventDate.isEmptyDate((EventDate) other)) {
            return true;
        }
        return value.equals(((EventDate) other).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
