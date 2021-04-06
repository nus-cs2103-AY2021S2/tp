package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Dog's breed in the database.
 * Guarantees: immutable;
 */
public class DateOfBirth {
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final String MESSAGE_CONSTRAINTS = "Date of Birth should be in the " + DATE_FORMAT + " format and "
            + "should not be after the current date.";

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
        try {
            LocalDate.parse(value, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets Age of Dog in years.
     *
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
