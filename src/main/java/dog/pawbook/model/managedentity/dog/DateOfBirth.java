package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Dog's breed in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDob(String)}
 */
public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS = "Date Of Birth should only "
            + "contain numbers and should not be blank.";
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
        LocalDate date = LocalDate.parse(value, DateTimeFormatter.ofPattern("d-M-yyyy"));
        this.date = date;
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
