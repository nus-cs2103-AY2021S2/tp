package seedu.address.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's CoeExpiry in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCoeExpiry(String)}
 */
public class CoeExpiry {

    public static final String MESSAGE_CONSTRAINTS = "Expiry dates should be of the format yyyy MM dd "
            + "EG:(2011 07 06 for 6th July, 2011)";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy MM dd");

    public final String expiryDate;

    /**
     * Constructs an {@code CoeExpiry}.
     *
     * @param coeExpiry A valid COE Expiry Date.
     */
    public CoeExpiry(String coeExpiry) {
        requireNonNull(coeExpiry);
        checkArgument(isValidCoeExpiry(coeExpiry), MESSAGE_CONSTRAINTS);
        expiryDate = coeExpiry;
    }

    /**
     * Returns if a given string is a valid COE Expiry Date.
     */
    public static boolean isValidCoeExpiry(String test) {
        try {
            LocalDate.parse(test, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return expiryDate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CoeExpiry // instanceof handles nulls
                && expiryDate.equals(((CoeExpiry) other).expiryDate)); // state check
    }

    @Override
    public int hashCode() {
        return expiryDate.hashCode();
    }

}
