package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Task's deadline in the planner.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {


    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should be in the format dd/mm/yyyy eg. 12/05/2021";
    public static final String MESSAGE_CONSTRAINTS_INVALID_DATE =
            "Deadline should not be before today";

    public static final String VALIDATION_REGEX = "\\d{2}\\/\\d{2}\\/\\d{4}";

    public final LocalDate value;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline number.
     */
    public Deadline(String deadline) throws DateTimeParseException {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        value = parseDeadline(deadline);
    }

    /**
     * Returns true if a given string is a valid deadline number.
     */
    public static boolean isValidDeadline(String test) {
        Pattern p = Pattern.compile(VALIDATION_REGEX);
        Matcher m = p.matcher(test);
        return m.matches();
    }

    /**
     * Returns a deadline in the form of a LocalDate.
     * @param deadline the specified deadline.
     * @return
     */
    public static LocalDate parseDeadline(String deadline) {
        LocalDate parsedDeadline = LocalDate.parse(deadline,
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return parsedDeadline;
    }

    @Override
    public String toString() {
        return value.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && value.equals(((Deadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
