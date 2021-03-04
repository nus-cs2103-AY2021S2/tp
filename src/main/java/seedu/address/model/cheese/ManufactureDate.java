package seedu.address.model.cheese;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import seedu.address.model.Date;

/**
 * Represents a Cheese's manufacture date in the Cheese inventory Management System (CHIM).
 * Guarantees: immutable
 */
public class ManufactureDate extends Date {

    /**
     * Constructs a {@code ManufactureDate}.
     *
     * @param date A valid manufacture date.
     */
    public ManufactureDate(String date) {
        super(date);
        checkArgument(isValidManufactureDate(this.value), MESSAGE_CONSTRAINTS);
    }

    public boolean isValidManufactureDate(LocalDateTime value) {
        return true;
    }
}
