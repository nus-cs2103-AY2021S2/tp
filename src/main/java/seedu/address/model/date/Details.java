package seedu.address.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Details implements Comparable<Details> {

    public static final String MESSAGE_CONSTRAINTS = "Details should be in yyyy-mm-dd HHmm format.";
    private static final String DATE_TIME_FORMAT = "MMM dd yyyy HHmm";
    private static final String INPUT_FORMAT = "yyyy-MM-dd HHmm";

    public final LocalDateTime details;

    /**
     * Constructs a {@code Details}.
     *
     * @param details A valid details.
     */
    public Details(String details) {
        requireNonNull(details);
        checkArgument(isValidDetails(details), MESSAGE_CONSTRAINTS);
        this.details = parseStringIntoLocalDateTime(details);
    }

    /**
     * Returns true if a given string is a valid details.
     */
    public static boolean isValidDetails(String details) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_FORMAT);
            LocalDateTime.parse(details, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Creates a LocalDateTime object from a string.
     *
     * @param details String that is used to create a LocalDateTime object.
     * @return A LocalDateTime object created.
     */
    public static LocalDateTime parseStringIntoLocalDateTime(String details) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(INPUT_FORMAT);
        LocalDateTime createDetails = LocalDateTime.parse(details, format);
        return createDetails;
    }

    /**
     * Parses LocalDateTime object into a string.
     *
     * @param details LocalDateTime object.
     * @return A String representing the date and time in the new format.
     */
    public static String parseLocalDateTimeIntoString(LocalDateTime details) {
        return details.format(DateTimeFormatter.ofPattern(INPUT_FORMAT));
    }

    @Override
    public int compareTo(Details other) {
        return this.details.compareTo(other.details);
    }

    @Override
    public String toString() {
        return details.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Details // instanceof handles nulls
                && details.equals(((Details) other).details)); // state check
    }

    @Override
    public int hashCode() {
        return details.hashCode();
    }

}

