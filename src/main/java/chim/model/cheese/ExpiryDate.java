package chim.model.cheese;

import static chim.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import chim.model.AbstractDate;

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
