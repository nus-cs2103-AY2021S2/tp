package seedu.storemando.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Item's expiry in the inventory manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidExpiryDate(String)}
 */
public class ExpiryDate {

    public static final String NO_EXPIRY_DATE = "No Expiry Date";
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
        + "    - be a valid expiryDate\n";

    public final String value;
    public final LocalDate expiryDate;

    /**
     * Constructs an {@code ExpiryDate}.
     *
     * @param expiryDate A valid expiryDate.
     */
    public ExpiryDate(String expiryDate) {
        requireNonNull(expiryDate);
        checkArgument(isValidExpiryDate(expiryDate), MESSAGE_CONSTRAINTS);
        if (expiryDate.equals(NO_EXPIRY_DATE)) {
            this.expiryDate = null;
        } else {
            this.expiryDate = LocalDate.parse(expiryDate);
        }
        value = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }

    /**
     * Returns if a given string is a valid expiryDate.
     */
    public static boolean isValidExpiryDate(String test) {
        if (test.equals(NO_EXPIRY_DATE)) {
            return true;
        }
        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    public String toFormattedString() {
        return expiryDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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
