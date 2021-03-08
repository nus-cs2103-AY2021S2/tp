package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTime {

    public static final String MESSAGE_CONSTRAINTS = "Event times should be "
            + "provided in the format DD/MM/YYYY hh:mm and adhere to the "
            + "following constraints: \n"
            + "1. DD should only contain values from 01 to 31, and correspond"
            + "to the correct range for each month:\n"
            + "    - 01 to 28 for Feb except for leap years.\n"
            + "    - 01 to 30 for Apr, Jun, Sep and Nov.\n"
            + "    - 01 to 31 for months: Jan, Mar, May, Jul, Aug, Oct and Dec.\n"
            + "2. MM should only contain values from 01 to 12.\n"
            + "3. YYYY should only contain values from 0000 to 9999.\n"
            + "4. hh should only contain values from 00 to 23.\n"
            + "5. mm should only contain values from 00 to 59.\n";

    /**
     * Matches DD/MM/YYYY hh:mm with the following constraints:
     * DD: 01 to 09 || 10 to 29 || 30 to 31
     * MM: 00 to 12 -> edge case month 00, checked in isValidDateTime
     * YYYY: 0000 to 9999 -> Java's max date is 99999999999999
     * hh: 00 to 19 || 20 to 23
     * mm: 00 to 59
     */
    public static final String VALIDATION_REGEX =
            "^(([0][1-9]|[1-2][0-9])|(3[01]))\\/(([0][1-9])|([1][0-2]))\\/\\d\\d\\d\\d "
                    + "(([0-1][0-9])|([2][0-3])):[0-5][0-9]$";

    /**
     * DateTimeFormatter for dd/MM/yyyy HHmm input format on date inputs
     */
    private static final DateTimeFormatter INPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public final LocalDateTime eventTime;

    /**
     * Constructs a {@code EventTime}
     *
     * @param eventTime A valid eventTime
     */
    public EventTime(String eventTime) {
        requireNonNull(eventTime);
        checkArgument(isValidEventTime(eventTime), MESSAGE_CONSTRAINTS);
        LocalDateTime parsedDateTime = LocalDateTime.now();
        // catch errors in parsing datetime
        try {
            // try to parse date time
            parsedDateTime = parseStringToLocalDateTime(eventTime);
        } catch (DateTimeParseException dateTimeParseException) {
            // use checkArgument to pass error upwards
            checkArgument(false, MESSAGE_CONSTRAINTS);
        }
        this.eventTime = parsedDateTime;
    }

    /**
     * Returns true if a given String is a valid eventTime
     */
    public static boolean isValidEventTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Parser for event time in the correct format into a LocalDateTime object
     *
     * @param eventTime eventTime that passed validation
     * @return local date time object from string eventTime in correct format
     * @throws DateTimeParseException if there's an error passing the eventTime
     */
    private static LocalDateTime parseStringToLocalDateTime(String eventTime)
            throws DateTimeParseException {
        return LocalDateTime.parse(
                eventTime,
                INPUT_DATE_FORMAT);
    }

    @Override
    public String toString() {
        return this.eventTime.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventTime // instanceof handles nulls
                && this.eventTime.equals(((EventTime) other).eventTime)); // state check
    }

    @Override
    public int hashCode() {
        return this.eventTime.hashCode();
    }

}