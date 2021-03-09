package seedu.address.model.meeting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Meeting {
    public static final String MESSAGE_CONSTRAINTS = "Meetings should be of the format "
        + "\"description @ yyyy-mm-dd HH:MM\", and the desciption should not me blank.";
    // alphanumeric and special characters
    private static final String DESCRIPTION_REGEX = ".+";
    private static final String FOUR_DIGIT_REGEX = "[0-9]{4}";
    private static final String TWO_DIGIT_REGEX = "[0-9]{2}";
    public static final String VALIDATION_REGEX = DESCRIPTION_REGEX + " @ "
            + FOUR_DIGIT_REGEX + "-" + TWO_DIGIT_REGEX + "-" + TWO_DIGIT_REGEX + " "
            + TWO_DIGIT_REGEX + ":" + TWO_DIGIT_REGEX;

    public static final String DATETIME_CONSTRAINTS = "The input date and time must be existent.";

    public final String value;
    public final LocalDateTime dateTime;

    /**
     * Constructs an {@code Meeting}.
     *
     * @param meeting A valid meeting.
     */
    public Meeting(String meeting) {
        requireNonNull(meeting);
        checkArgument(isValidMeeting(meeting), MESSAGE_CONSTRAINTS);
        String[] fragments = meeting.split(" @ ", 2);
        LocalDateTime parsedDt = generateDateTime(fragments[1], DATETIME_CONSTRAINTS);
        value = fragments[0];
        dateTime = parsedDt;
    }

    /**
     * Returns if a given string is a valid meeting.
     */
    public static boolean isValidMeeting(String meeting) {
        return meeting.matches(VALIDATION_REGEX);
    }

    public static LocalDateTime generateDateTime(String datetime, String errorMessage) {
        try {
            return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-d H:m"));
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    @Override
    public String toString() {
        return value + "@" + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Meeting // instanceof handles nulls
                && value.equals(((Meeting) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode() + dateTime.hashCode();
    }

}
