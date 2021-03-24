package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a meeting with a client in the ClientBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidMeeting(String, String, String)}
 */
public class Meeting {

    public static final String MESSAGE_CONSTRAINTS =
            "Meetings should only contain place, date and time, and it should not be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String DATE_REGEX = "^(0[1-9]|[1-2][0-9]|31(?!(?:0[2469]|11))|30(?!02))/"
            + "(0[1-9]|1[0-2])/([12]\\d{3})$";

    public static final String TIME_REGEX = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";

    public final String place;
    public final String date;
    public final String time;
    public final String meeting;

    /**
     * Constructs a {@code Meeting}.
     *
     * @param place A valid place.
     * @param date A valid date.
     * @param time A valid time.
     */
    public Meeting(String place, String date, String time) {
        requireNonNull(place);
        requireNonNull(date);
        requireNonNull(time);

        checkArgument(isValidMeeting(place, date, time), MESSAGE_CONSTRAINTS);

        this.place = place;
        this.date = date;
        this.time = time;
        this.meeting = place + ";" + date + ";" + time;
    }

    /**
     * Returns a new meeting by parsing the given string.
     */
    public static Meeting newMeeting(String meeting) {
        Meeting meet;
        try {
            meet = ParserUtil.parseMeet(meeting);
        } catch (ParseException pe) {
            meet = null;
        }
        return meet;
    }

    /**
     * Returns true if the given strings can form a valid meeting.
     */
    public static boolean isValidMeeting(String place, String date, String time) {
        return place.matches(VALIDATION_REGEX) && date.matches(DATE_REGEX) && time.matches(TIME_REGEX);
    }

    /**
     * Returns true if a given string is a valid meeting.
     */
    public static boolean isValidMeeting(String meeting) {
        Meeting meet = newMeeting(meeting);
        if (meet == null) {
            return false;
        }
        return meet.place.matches(VALIDATION_REGEX) && meet.date.matches(DATE_REGEX) && meet.time.matches(TIME_REGEX);
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getMeeting() {
        return meeting;
    }

    @Override
    public String toString() {
        return place + " " + date + " " + time;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Meeting // instanceof handles nulls
                && place.equals(((Meeting) other).place) // state check
                && date.equals(((Meeting) other).date) // state check
                && time.equals(((Meeting) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, date, time);
    }

}
