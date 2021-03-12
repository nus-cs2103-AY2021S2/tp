package seedu.address.model.cheese;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import seedu.address.model.AbstractDate;

/**
 * Represents a Cheese's expiry date in the Cheese Inventory Management System (CHIM).
 * Guarantees: immutable
 */
public class ExpiryDate extends AbstractDate {

    /**
     * Constructs a {@code ExpiryDate}.
     *
     * @param date A valid expiry date.
     */
    public ExpiryDate(String date) {
        super(date);
        checkArgument(isValidManufactureDate(this.value), MESSAGE_CONSTRAINTS);
    }

    public boolean isValidManufactureDate(LocalDateTime value) {
        return true;
    }
}
