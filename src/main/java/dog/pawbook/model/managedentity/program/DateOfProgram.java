package dog.pawbook.model.managedentity.program;

import static dog.pawbook.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Program's date and time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class DateOfProgram {

    public static final String MESSAGE_CONSTRAINTS = "Date Of Program should only "
        + "contain numbers and should not be blank.";
    //Validation regex for dd-mm-yyyy hh:mm or mm-dd-yyyy  hh:mm format
    public static final String VALIDATION_REGEX = "^([1-9]|([012][0-9])|(3[01]))-([0]{0,1}[1-9]|1[012])-\\d\\d\\d\\d "
        + "[012]{0,1}[0-9]:[0-6][0-9]$";
    public final LocalDateTime dateTime;
    public final String value;

    /**
     * Construct an {@code DateOfProgram}.
     *
     * @param value A valid date and time.
     */
    public DateOfProgram(String value) {
        requireNonNull(value);
        checkArgument(isValidDate(value), MESSAGE_CONSTRAINTS);
        this.value = value;
        LocalDateTime dateTime = LocalDateTime.parse(value, DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
        this.dateTime = dateTime;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String value) {
        Pattern pattern = Pattern.compile(VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public LocalDateTime getDate() {
        return dateTime;
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DateOfProgram) // instanceof handles nulls
            && value.equals(((DateOfProgram) other).value); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

