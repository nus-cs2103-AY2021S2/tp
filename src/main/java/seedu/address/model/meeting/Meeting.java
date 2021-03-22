package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a meeting with a client in the ClientBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidMeeting(String)}
 */
public class Meeting {

    public static final String MESSAGE_CONSTRAINTS =
            "Meetings should only contain date and time, and it should not be blank";

    public static final String DATE_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)" +
            "(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9" +
            "]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[" +
            "0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

    public static final String TIME_REGEX = "^(?:(?:([01]?\\d|2[0-3]):)?([0-5]?\\d):)?([0-5]?\\d)$";

    public final String meeting;

    /**
     * Constructs a {@code Meeting}.
     *
     * @param meeting A valid meeting.
     */
    public Meeting(String meeting) {
        requireNonNull(meeting);
        checkArgument(isValidMeeting(meeting), MESSAGE_CONSTRAINTS);
        this.meeting = meeting;
    }

    /**
     * Returns true if a given string is a valid meeting.
     */
    public static boolean isValidMeeting(String test) {
        String[] arguments = test.split("\\s+");
        return arguments[0].matches(DATE_REGEX) && arguments[1].matches(TIME_REGEX);
    }

    @Override
    public String toString() {
        return meeting;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.meeting.Meeting // instanceof handles nulls
                && meeting.equals(((seedu.address.model.meeting.Meeting) other).meeting)); // state check
    }

    @Override
    public int hashCode() {
        return meeting.hashCode();
    }

}
