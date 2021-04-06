package seedu.address.model.person;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.TimeUtil;

public class Meeting extends Event {

    public static final String DESCRIPTION_VALIDATION_REGEX = Event.DESCRIPTION_VALIDATION_REGEX;
    public static final String DESCRIPTION_MESSAGE_CONSTRAINTS = Event.DESCRIPTION_MESSAGE_CONSTRAINTS;
    public static final String MESSAGE_CONSTRAINTS = "Meeting must have occurred already "
            + "and meeting description cannot be empty";

    public Meeting(LocalDate date, LocalTime time, String description) {
        super(date, time, description);
    }

    /**
     * Returns true if the given parameters can construct a valid {@code Meeting}
     */
    public static boolean isValidMeeting(LocalDate date, LocalTime time, String description) {
        // Checks for
        // 1. date is before today
        // 2. if date is today, time is before current time
        // 3. description is not empty
        return !DateUtil.afterToday(date)
                && !(DateUtil.isToday(date) && TimeUtil.afterNow(time))
                && description.matches(DESCRIPTION_VALIDATION_REGEX);
    }

    @Override
    public String toUi() {
        return String.format("%s %s %s\n", DateUtil.toUi(date), TimeUtil.toUi(time), description);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getDate().equals(getDate())
                && otherMeeting.getTime().equals(getTime())
                && otherMeeting.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(date)
                .append("; ")
                .append(time)
                .append("; ")
                .append(description);
        return builder.toString();
    }
}
