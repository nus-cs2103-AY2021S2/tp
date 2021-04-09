package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Patient's date of birth in DocBob.
 * Guarantees: immutable; is valid if date of birth is valid as declared in {@link #isValidDateOfBirth(LocalDate)}
 */
public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS = "Date of Birth must be in the form DDMMYYYY and be in the past";

    public static final String PAST_DATE_CONSTRAINT = "Remember that the Date of Birth entered must be in the past";

    public final LocalDate dateOfBirth;

    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param dateOfBirth A valid date of birth.
     */
    public DateOfBirth(LocalDate dateOfBirth) {
        requireNonNull(dateOfBirth);
        checkArgument(isValidDateOfBirth(dateOfBirth), PAST_DATE_CONSTRAINT);
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns true if a given date is a valid date of birth that is in the past.
     */
    public static boolean isValidDateOfBirth(LocalDate test) {
        return test.isBefore(LocalDate.now());
    }


    @Override
    public String toString() {
        return dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateOfBirth // instanceof handles nulls
                && dateOfBirth.equals(((DateOfBirth) other).dateOfBirth)); // state check
    }

    @Override
    public int hashCode() {
        return dateOfBirth.hashCode();
    }

}
