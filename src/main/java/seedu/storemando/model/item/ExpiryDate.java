package seedu.storemando.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents an Item's expiry date in the inventory manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidExpiryDate(String)}
 */
public class ExpiryDate {

    public static final String VALIDATION_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    private static final String SPECIAL_CHARACTERS = "-";
    public static final String MESSAGE_CONSTRAINTS = "Expiry dates should be of the format YYYY-MM-DD "
        + "and adhere to the following constraints:\n"
        + "1. YYYY, MM and DD should only contain numeric characters and must be separated by a "
        + SPECIAL_CHARACTERS
        + " character.\n"
        + "2. YYYY must:\n"
        + "    - be exactly 4 digits long\n"
        + "    - be a valid year\n"
        + "3. MM must:\n"
        + "    - be exactly 2 digits long\n"
        + "    - be within the range [01, 12]\n"
        + "4. DD must:\n"
        + "    - be exactly 2 digits long\n"
        + "    - be a valid date\n";


    public final LocalDate value;

    /**
     * Constructs an {@code ExpiryDate}.
     *
     * @param expiryDate A valid expiryDate.
     */
    public ExpiryDate(String expiryDate) {
        requireNonNull(expiryDate);
        checkArgument(isValidExpiryDate(expiryDate), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(expiryDate);
    }

    /**
     * Returns if a given string is a valid expiryDate.
     */
    public static boolean isValidExpiryDate(String test) {
        requireNonNull(test);
        if (test == null || !test.matches(VALIDATION_REGEX)) {
            return false;
        }
        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ExpiryDate // instanceof handles nulls
            && value.equals(((ExpiryDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
