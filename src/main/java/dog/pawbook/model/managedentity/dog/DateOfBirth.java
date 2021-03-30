package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Dog's breed in the database.
 * Guarantees: immutable;
 */
public class DateOfBirth {
    public static final String DATE_FORMAT = "d-M-yyyy";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final String MESSAGE_CONSTRAINTS = "Date Of Birth should be in the " + DATE_FORMAT + " format.";
    public static final String VALIDATION_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)"
            + "(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3"
            + "(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))"
            + "$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

    public final LocalDate date;
    public final String value;

    /**
     * Construct an {@code DateOfBirth}.
     *
     * @param value A valid date of birth (dob).
     */
    public DateOfBirth(String value) {
        requireNonNull(value);
        checkArgument(isValidDob(value), MESSAGE_CONSTRAINTS);
        this.value = value;
        this.date = LocalDate.parse(value, DATE_FORMATTER);
    }

    /**
     * Construct an {@code DateOfBirth} from a given {@code LocalDate}.
     */
    public DateOfBirth(LocalDate date) {
        requireNonNull(date);
        this.date = date;
        this.value = date.format(DATE_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDob(String value) {
        Pattern pattern = Pattern.compile(VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets Age of Dog in years.
     * @return Age of dog in years.
     */
    public int getAge() {
        Period period = Period.between(date, LocalDate.now());
        return period.getYears();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateOfBirth // instanceof handles nulls
                && value.equals(((DateOfBirth) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
