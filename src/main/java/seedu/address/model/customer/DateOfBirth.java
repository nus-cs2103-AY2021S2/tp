package seedu.address.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Customer's DateOfBirth in the customer list. Guarantees: immutable; is valid as declared in {@link
 * #isValidDateOfBirth(String)}
 */
public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS = "Birth date should be of the format yyyy MM dd "
        + "EG:(2011 07 06 for 6th July, 2011)";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy MM dd")
            .withResolverStyle(ResolverStyle.STRICT);

    public final String birthDate;

    /**
     * Constructs an {@code DateOfBirth}.
     *
     * @param dateOfBirth A valid date of birth.
     */
    public DateOfBirth(String dateOfBirth) {
        requireNonNull(dateOfBirth);
        checkArgument(isValidDateOfBirth(dateOfBirth), MESSAGE_CONSTRAINTS);
        birthDate = dateOfBirth;
    }

    /**
     * Returns if a given string is a valid date of birth.
     */
    public static boolean isValidDateOfBirth(String test) {
        try {
            LocalDate.parse(test, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return birthDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DateOfBirth // instanceof handles nulls
            && birthDate.equals(((DateOfBirth) other).birthDate)); // state check
    }

    @Override
    public int hashCode() {
        return birthDate.hashCode();
    }

    public String getBirthDate() {
        return this.birthDate;
    }

}
