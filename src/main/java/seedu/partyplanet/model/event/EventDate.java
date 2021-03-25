package seedu.partyplanet.model.event;

import static seedu.partyplanet.commons.util.AppUtil.checkArgument;

import seedu.partyplanet.model.date.Date;

/** Represents an Event's date in PartyPlanet.
 * Guarantees: immutable; is always valid.
 */
public class EventDate extends Date {

    public static final String MESSAGE_CONSTRAINTS = "Event dates should be in one of the following formats:\n"
            + MESSAGE_YEAR_FORMATS + "\n" + MESSAGE_NOYEAR_FORMATS;
    public static final String MESSAGE_YEAR_CONSTRAINTS = "A year is required for the input\n";
    public static final EventDate EMPTY_EVENT_DATE = new EventDate();

    /**
     * Constructs a {@code EventDate}.
     * EventDate must contain a year.
     * Some invalid dates are mapped to the nearest valid date, e.g. 29 Feb 2021 -> 28 Feb 2021.
     *
     * @param eventDate A valid eventDate.
     */
    public EventDate(String eventDate) {
        super(eventDate);
        checkArgument(hasYear(), MESSAGE_YEAR_CONSTRAINTS);
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
}
